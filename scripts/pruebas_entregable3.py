#!/usr/bin/env python3
"""
Pruebas de integración del Entregable 3 de TwoVago.
Usa únicamente la biblioteca estándar de Python.
"""
import json
import os
import subprocess
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from http.cookiejar import CookieJar
from datetime import date, timedelta
from pathlib import Path

BASE = os.getenv("TWOVAGO_API_URL", "http://localhost:8083").rstrip("/")
ROOT = Path(__file__).resolve().parents[1]
REPORT = ROOT / "documentacion" / "reportes" / "resultados-pruebas-e3.txt"

results = []
passed = 0
failed = 0
skipped = 0


class ApiClient:
    def __init__(self):
        self.cookies = CookieJar()
        self.opener = urllib.request.build_opener(
            urllib.request.HTTPCookieProcessor(self.cookies)
        )

    def request(self, method, path, data=None):
        url = BASE + path
        body = None if data is None else json.dumps(data).encode("utf-8")
        req = urllib.request.Request(
            url,
            data=body,
            method=method,
            headers={"Content-Type": "application/json", "Accept": "application/json"},
        )
        try:
            with self.opener.open(req, timeout=15) as response:
                raw = response.read().decode("utf-8", errors="replace")
                return response.status, parse_body(raw)
        except urllib.error.HTTPError as exc:
            raw = exc.read().decode("utf-8", errors="replace")
            return exc.code, parse_body(raw)


def parse_body(raw):
    if not raw:
        return None
    try:
        return json.loads(raw)
    except json.JSONDecodeError:
        return raw


def add_result(name, ok, detail=""):
    global passed, failed
    if ok:
        passed += 1
        state = "OK"
    else:
        failed += 1
        state = "FALLO"
    results.append(f"[{state}] {name}" + (f" — {detail}" if detail else ""))


def skip_result(name, detail):
    global skipped
    skipped += 1
    results.append(f"[OMITIDA] {name} — {detail}")


def check_status(client, name, method, path, expected, data=None):
    try:
        status, body = client.request(method, path, data)
        ok = status in (expected if isinstance(expected, tuple) else (expected,))
        detail = f"HTTP {status}"
        if not ok:
            detail += f"; esperado {expected}; respuesta={str(body)[:300]}"
        add_result(name, ok, detail)
        return status, body
    except Exception as exc:
        add_result(name, False, f"Error de conexión o ejecución: {exc}")
        return None, None


def check_condition(name, condition, detail):
    add_result(name, bool(condition), detail)


def mysql_seed_demo_rooms():
    mysql = os.getenv("MYSQL_BIN", "mysql")
    password = os.getenv("TWOVAGO_DB_PASSWORD")
    if not password:
        return False, "No se definió TWOVAGO_DB_PASSWORD para insertar habitaciones de prueba"
    host = os.getenv("TWOVAGO_DB_HOST", "172.23.176.1")
    port = os.getenv("TWOVAGO_DB_PORT", "3306")
    user = os.getenv("TWOVAGO_DB_USER", "twovago_app")
    database = os.getenv("TWOVAGO_DB_NAME", "twovago")
    sql_file = ROOT / "database" / "datos_prueba_entregable3.sql"
    env = os.environ.copy()
    env["MYSQL_PWD"] = password
    try:
        with sql_file.open("rb") as source:
            proc = subprocess.run(
                [mysql, "-h", host, "-P", str(port), "-u", user, database],
                stdin=source, env=env, capture_output=True, text=False, timeout=20
            )
        if proc.returncode != 0:
            return False, proc.stderr.decode("utf-8", errors="replace").strip() or "mysql devolvió un error"
        return True, "Se agregaron habitaciones de demostración que no existían"
    except FileNotFoundError:
        return False, "No se encontró el cliente mysql en PATH"
    except Exception as exc:
        return False, str(exc)


def mysql_expire_reservation(id_reservacion):
    mysql = os.getenv("MYSQL_BIN", "mysql")
    password = os.getenv("TWOVAGO_DB_PASSWORD")
    if not password:
        return False, "No se definió TWOVAGO_DB_PASSWORD; no se puede adelantar el vencimiento"
    host = os.getenv("TWOVAGO_DB_HOST", "172.23.176.1")
    port = os.getenv("TWOVAGO_DB_PORT", "3306")
    user = os.getenv("TWOVAGO_DB_USER", "twovago_app")
    database = os.getenv("TWOVAGO_DB_NAME", "twovago")
    env = os.environ.copy()
    env["MYSQL_PWD"] = password
    sql = (
        "UPDATE reservaciones "
        "SET fecha_limite_pago = DATE_SUB(NOW(), INTERVAL 1 MINUTE) "
        f"WHERE id_reservacion = {int(id_reservacion)};"
    )
    try:
        proc = subprocess.run(
            [mysql, "-h", host, "-P", str(port), "-u", user, database, "-e", sql],
            env=env, capture_output=True, text=True, timeout=15
        )
        if proc.returncode != 0:
            return False, (proc.stderr.strip() or "mysql devolvió un error")
        return True, "Se adelantó la fecha límite de pago en la base de datos"
    except FileNotFoundError:
        return False, "No se encontró el cliente mysql en PATH"
    except Exception as exc:
        return False, str(exc)


def main():
    global passed, failed, skipped
    started = time.strftime("%Y-%m-%d %H:%M:%S")
    client = ApiClient()
    anonymous = ApiClient()

    # Conectividad y consultas públicas
    status, _ = check_status(client, "API disponible", "GET", "/api/health", 200)
    if status != 200:
        results.append(
            "Pruebas detenidas: esta dirección no está ejecutando la versión R3 actual. "
            "Usa bash scripts/validar_entregable3.sh para compilar e iniciar el backend nuevo."
        )
        write_report(started)
        return 1

    _, hotels = check_status(client, "Consulta pública de hoteles", "GET", "/api/hoteles", 200)
    _, rooms = check_status(client, "Consulta pública de habitaciones", "GET", "/api/habitaciones", 200)
    if rooms == []:
        seeded, seed_detail = mysql_seed_demo_rooms()
        if seeded:
            _, rooms = check_status(client, "Consulta de habitaciones después de cargar datos de prueba",
                                    "GET", "/api/habitaciones", 200)
            results.append(f"[INFO] Datos de demostración — {seed_detail}")
        else:
            results.append(f"[INFO] Datos de demostración no cargados — {seed_detail}")
    check_status(client, "Consulta de hotel inexistente", "GET", "/api/hoteles/999999999", 404)
    check_status(client, "Filtro público de hoteles por ciudad", "GET", "/api/hoteles?ciudad=Xalapa", 200)

    # Protección de rutas sin sesión
    check_status(anonymous, "Reservar sin iniciar sesión", "POST", "/api/reservaciones", 401, {
        "id_habitacion": 1,
        "fecha_entrada": str(date.today() + timedelta(days=60)),
        "fecha_salida": str(date.today() + timedelta(days=62)),
        "cantidad_huespedes": 1,
    })

    # Registro de usuario
    suffix = str(time.time_ns())[-10:]
    email1 = f"prueba.e3.{suffix}@twovago.test"
    password = "PruebaE3-2026!"
    registration = {
        "nombre": "Usuario de Pruebas E3",
        "correo": email1,
        "password": password,
        "telefono": "2281234567",
    }
    status, user = check_status(client, "Registro de usuario", "POST", "/api/auth/registro", 201, registration)
    if status == 201 and isinstance(user, dict):
        check_condition("La respuesta de registro no expone contraseña", "password" not in user and "password_hash" not in user,
                        "Se revisó el JSON de respuesta")
    check_status(client, "Registro duplicado", "POST", "/api/auth/registro", 409, registration)
    check_status(client, "Registro con correo inválido", "POST", "/api/auth/registro", 400, {
        "nombre": "Prueba inválida", "correo": "correo_invalido", "password": "PruebaE3-2026!"
    })

    # Login y perfil
    check_status(client, "Inicio de sesión con contraseña incorrecta", "POST", "/api/auth/login", 401, {
        "correo": email1, "password": "Incorrecta-123!"
    })
    status, login = check_status(client, "Inicio de sesión correcto", "POST", "/api/auth/login", 200, {
        "correo": email1, "password": password
    })
    if status == 200:
        check_condition("El login devuelve el rol CLIENTE", isinstance(login, dict) and login.get("rol") == "CLIENTE",
                        f"Respuesta={login}")
    _, profile = check_status(client, "Consulta de perfil propio", "GET", "/api/usuarios/perfil", 200)
    check_status(client, "Actualización del perfil", "PUT", "/api/usuarios/perfil", 200, {
        "nombre": "Usuario E3 Actualizado", "telefono": "2287654321"
    })
    check_status(client, "Rechazar fechas de reservación invertidas", "POST", "/api/reservaciones", 400, {
        "id_habitacion": 1,
        "fecha_entrada": (date.today() + timedelta(days=70)).isoformat(),
        "fecha_salida": (date.today() + timedelta(days=69)).isoformat(),
        "cantidad_huespedes": 1,
    })
    check_status(client, "Un cliente no puede crear hoteles", "POST", "/api/hoteles", 403, {
        "nombre": "Hotel no autorizado", "ciudad": "Xalapa",
        "direccion": "Calle de prueba", "categoria": 3, "activo": True
    })
    check_status(client, "Un cliente no puede registrar habitaciones", "POST",
                 "/api/habitaciones?hotelId=1", 403, {
                     "numero": "999", "tipo": "Doble", "capacidad": 2,
                     "precioNoche": 500.00, "descripcion": "Prueba de permisos"
                 })

    # Segundo usuario para comprobar privacidad y conflicto de disponibilidad
    client2 = ApiClient()
    email2 = f"prueba.e3.segundo.{suffix}@twovago.test"
    registration2 = {
        "nombre": "Segundo usuario E3",
        "correo": email2,
        "password": password,
        "telefono": "2281234568",
    }
    s2, _ = check_status(client2, "Registro de segundo usuario", "POST", "/api/auth/registro", 201, registration2)
    segundo_usuario_autenticado = False
    if s2 == 201:
        s2_login, _ = check_status(
            client2, "Inicio de sesión del segundo usuario", "POST", "/api/auth/login", 200,
            {"correo": email2, "password": password}
        )
        segundo_usuario_autenticado = s2_login == 200

    # Reservaciones, pagos, cancelación y disponibilidad
    selected = None
    if isinstance(hotels, list) and isinstance(rooms, list):
        for hotel in hotels:
            if not isinstance(hotel, dict):
                continue
            hotel_id = hotel.get("idHotel")
            for room in rooms:
                if not isinstance(room, dict):
                    continue
                linked_hotel = room.get("hotel") or {}
                if linked_hotel.get("idHotel") != hotel_id:
                    continue
                room_id = room.get("idHabitacion")
                capacity = room.get("capacidad") or 1
                for offset in (45, 75, 105, 135, 165, 195):
                    entry = date.today() + timedelta(days=offset)
                    exit_date = entry + timedelta(days=2)
                    query = urllib.parse.urlencode({
                        "hotelId": hotel_id,
                        "fechaEntrada": entry.isoformat(),
                        "fechaSalida": exit_date.isoformat(),
                        "huespedes": min(1, capacity),
                    })
                    _, available = check_status(client, f"Consulta de disponibilidad ({hotel.get('nombre','hotel')})",
                                                "GET", "/api/habitaciones/disponibles?" + query, 200)
                    if isinstance(available, list) and any(
                        isinstance(item, dict) and item.get("idHabitacion") == room_id for item in available
                    ):
                        selected = (hotel_id, room_id, entry, exit_date, min(1, capacity))
                        break
                if selected:
                    break
            if selected:
                break

    if not selected:
        skip_result("Flujo de reservación y pago", "No hay habitaciones activas disponibles en los hoteles registrados")
        skip_result("Conflicto por doble reservación", "Requiere una habitación y fechas disponibles")
        skip_result("Cancelación e historial", "Requiere crear una reservación")
        skip_result("Expiración de pago", "Requiere crear una reservación pendiente")
    else:
        hotel_id, room_id, entry, exit_date, guests = selected
        reservation_data = {
            "id_habitacion": room_id,
            "fecha_entrada": entry.isoformat(),
            "fecha_salida": exit_date.isoformat(),
            "cantidad_huespedes": guests,
        }
        s_res, reservation = check_status(client, "Crear reservación pendiente de pago",
                                          "POST", "/api/reservaciones", 201, reservation_data)
        if s_res == 201 and isinstance(reservation, dict):
            rid = reservation.get("id_reservacion")
            pid = reservation.get("id_pago")
            check_condition("La reservación inicia como PENDIENTE_PAGO",
                            reservation.get("estado") == "PENDIENTE_PAGO", f"Estado={reservation.get('estado')}")
            check_condition("El servidor calcula un total positivo",
                            float(reservation.get("total", 0)) > 0, f"Total={reservation.get('total')}")
            check_condition("La reservación genera un plazo de 10 minutos",
                            bool(reservation.get("fecha_limite_pago")), f"Fecha límite={reservation.get('fecha_limite_pago')}")
            check_status(client, "Consultar reservación propia", "GET", f"/api/reservaciones/{rid}", 200)
            check_status(client, "Consultar pago propio", "GET", f"/api/pagos/{pid}", 200)

            if segundo_usuario_autenticado:
                check_status(client2, "Otro usuario no puede consultar reservación ajena",
                             "GET", f"/api/reservaciones/{rid}", 403)
                check_status(client2, "Otro usuario no puede consultar pago ajeno",
                             "GET", f"/api/pagos/{pid}", 403)
                check_status(client2, "Impedir doble reservación de la misma habitación",
                             "POST", "/api/reservaciones", 409, reservation_data)
            else:
                skip_result("Privacidad entre usuarios y doble reservación",
                            "No se pudo autenticar al segundo usuario de prueba")

            check_status(client, "Rechazar método de pago no permitido",
                         "POST", f"/api/pagos/{pid}/confirmar", 400, {"metodo_simulado": "DINERO_REAL"})
            sp, payment = check_status(client, "Confirmar pago simulado",
                                       "POST", f"/api/pagos/{pid}/confirmar", 200,
                                       {"metodo_simulado": "TARJETA_SIMULADA"})
            if sp == 200:
                check_condition("El pago queda APROBADO",
                                isinstance(payment, dict) and payment.get("estado") == "APROBADO",
                                f"Estado={payment.get('estado') if isinstance(payment, dict) else payment}")
            _, current = check_status(client, "Consultar mis reservaciones", "GET",
                                       "/api/reservaciones/mis-reservas", 200)
            check_condition("La reservación confirmada aparece en mis reservas",
                            isinstance(current, list) and any(
                                isinstance(item, dict) and item.get("id_reservacion") == rid for item in current
                            ), "Se buscó el identificador recién creado")

            sc, cancelled = check_status(client, "Cancelar reservación confirmada antes de la entrada",
                                         "PUT", f"/api/reservaciones/{rid}/cancelar", 200)
            if sc == 200:
                check_condition("La reservación cambia a CANCELADA",
                                isinstance(cancelled, dict) and cancelled.get("estado") == "CANCELADA",
                                f"Estado={cancelled.get('estado') if isinstance(cancelled, dict) else cancelled}")
            _, history = check_status(client, "Consultar cancelaciones y expiraciones",
                                      "GET", "/api/reservaciones/cancelaciones", 200)
            check_condition("La cancelación aparece en el historial",
                            isinstance(history, list) and any(
                                isinstance(item, dict) and item.get("id_reservacion") == rid for item in history
                            ), "Se buscó el identificador recién cancelado")

            # La habitación debe quedar libre al cancelar.
            query = urllib.parse.urlencode({
                "hotelId": hotel_id, "fechaEntrada": entry.isoformat(),
                "fechaSalida": exit_date.isoformat(), "huespedes": guests
            })
            _, available_after = check_status(client, "Consultar disponibilidad después de cancelar",
                                               "GET", "/api/habitaciones/disponibles?" + query, 200)
            check_condition("La habitación se libera después de cancelar",
                            isinstance(available_after, list) and any(
                                isinstance(item, dict) and item.get("idHabitacion") == room_id
                                for item in available_after
                            ), f"Habitación {room_id}")

            # Prueba acelerada del vencimiento: requiere mysql CLI y credenciales de BD.
            s_exp, exp_res = check_status(client, "Crear reservación para probar expiración",
                                          "POST", "/api/reservaciones", 201, reservation_data)
            if s_exp == 201 and isinstance(exp_res, dict):
                expired_db, detail = mysql_expire_reservation(exp_res.get("id_reservacion"))
                if expired_db:
                    reservation_expired = None
                    payment_expired = None
                    for _ in range(12):
                        _, reservation_expired = client.request(
                            "GET", f"/api/reservaciones/{exp_res.get('id_reservacion')}"
                        )
                        _, payment_expired = client.request(
                            "GET", f"/api/pagos/{exp_res.get('id_pago')}"
                        )
                        if (isinstance(reservation_expired, dict)
                                and reservation_expired.get("estado") == "EXPIRADA"
                                and isinstance(payment_expired, dict)
                                and payment_expired.get("estado") == "EXPIRADO"):
                            break
                        time.sleep(1)
                    check_condition("La tarea programada expira automáticamente la reservación",
                                    isinstance(reservation_expired, dict)
                                    and reservation_expired.get("estado") == "EXPIRADA",
                                    f"Estado={reservation_expired.get('estado') if isinstance(reservation_expired, dict) else reservation_expired}")
                    check_condition("La tarea programada expira el pago asociado",
                                    isinstance(payment_expired, dict)
                                    and payment_expired.get("estado") == "EXPIRADO",
                                    f"Estado={payment_expired.get('estado') if isinstance(payment_expired, dict) else payment_expired}")
                    check_status(client, "Rechazar pago después del vencimiento",
                                 "POST", f"/api/pagos/{exp_res.get('id_pago')}/confirmar", 409,
                                 {"metodo_simulado": "TARJETA_SIMULADA"})
                else:
                    skip_result("Expiración acelerada de pago", detail)
            else:
                skip_result("Expiración acelerada de pago", "No se pudo crear una reservación de prueba")
        else:
            skip_result("Resto de pruebas de reservación", "Falló la creación inicial; revisa el mensaje HTTP anterior")

    # Cierre de sesión y bloqueo posterior
    check_status(client, "Cerrar sesión", "POST", "/api/auth/logout", 204)
    check_status(client, "Bloquear perfil después de cerrar sesión", "GET", "/api/usuarios/perfil", 401)

    # Pruebas de administrador opcionales, si se proporcionan credenciales de un usuario promovido a ADMIN.
    admin_email = os.getenv("TWOVAGO_ADMIN_EMAIL")
    admin_password = os.getenv("TWOVAGO_ADMIN_PASSWORD")
    if admin_email and admin_password:
        admin = ApiClient()
        sa, _ = check_status(admin, "Inicio de sesión de administrador", "POST", "/api/auth/login", 200,
                             {"correo": admin_email, "password": admin_password})
        if sa == 200:
            check_status(admin, "Consulta administrativa de reservaciones", "GET",
                         "/api/reservaciones/admin", 200)
            admin_hotel = {
                "nombre": f"Hotel Prueba Admin {suffix}", "ciudad": "Xalapa",
                "direccion": "Dirección de prueba 123", "descripcion": "Registro temporal de pruebas E3",
                "categoria": 3, "servicios": "WiFi", "activo": True
            }
            sh, hotel_admin = check_status(admin, "Administrador registra hotel", "POST",
                                           "/api/hoteles", 201, admin_hotel)
            if sh == 201 and isinstance(hotel_admin, dict):
                hid = hotel_admin.get("idHotel")
                admin_hotel["nombre"] += " Actualizado"
                check_status(admin, "Administrador actualiza hotel", "PUT",
                             f"/api/hoteles/{hid}", 200, admin_hotel)
                room_data = {
                    "numero": "E3-01", "tipo": "Doble", "capacidad": 2,
                    "precioNoche": 700.00, "descripcion": "Habitación temporal de pruebas"
                }
                sr, room_admin = check_status(admin, "Administrador registra habitación",
                                              "POST", f"/api/habitaciones?hotelId={hid}", 201, room_data)
                if sr == 201 and isinstance(room_admin, dict):
                    room_id_admin = room_admin.get("idHabitacion")
                    room_data["precioNoche"] = 750.00
                    check_status(admin, "Administrador actualiza habitación", "PUT",
                                 f"/api/habitaciones/{room_id_admin}", 200, room_data)
                    check_status(admin, "Administrador desactiva habitación", "DELETE",
                                 f"/api/habitaciones/{room_id_admin}", 204)
                check_status(admin, "Administrador desactiva hotel", "DELETE",
                             f"/api/hoteles/{hid}", 204)
    else:
        skip_result("CRUD positivo de administrador",
                    "Define TWOVAGO_ADMIN_EMAIL y TWOVAGO_ADMIN_PASSWORD para probarlo; el bloqueo para CLIENTE sí se probó")

    write_report(started)
    return 1 if failed else 0


def write_report(started):
    ended = time.strftime("%Y-%m-%d %H:%M:%S")
    lines = [
        "TWOVAGO — RESULTADOS DE PRUEBAS DEL ENTREGABLE 3",
        f"Inicio: {started}",
        f"Fin: {ended}",
        f"API: {BASE}",
        "",
        *results,
        "",
        f"RESUMEN: {passed} correctas | {failed} fallidas | {skipped} omitidas",
    ]
    REPORT.parent.mkdir(parents=True, exist_ok=True)
    REPORT.write_text("\n".join(lines) + "\n", encoding="utf-8")
    print("\n".join(lines))
    print(f"\nReporte guardado en: {REPORT}")


if __name__ == "__main__":
    sys.exit(main())
