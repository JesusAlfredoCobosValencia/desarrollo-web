#!/usr/bin/env bash
set -Eeuo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TEST_PORT="${TWOVAGO_TEST_PORT:-8083}"
LOG_FILE="/tmp/twovago-entregable3-backend.log"
PID=""

cleanup() {
  if [[ -n "$PID" ]]; then
    kill "$PID" 2>/dev/null || true
    wait "$PID" 2>/dev/null || true
  fi
}
trap cleanup EXIT INT TERM

# La contraseña se solicita sin mostrarla y no queda escrita en el script.
if [[ -z "${TWOVAGO_DB_PASSWORD:-}" ]]; then
  if [[ ! -t 0 ]]; then
    echo "Falta TWOVAGO_DB_PASSWORD. Ejecuta el script desde una terminal o define esa variable."
    exit 2
  fi
  read -r -s -p "Contraseña MySQL del usuario twovago_app: " TWOVAGO_DB_PASSWORD
  printf '\n'
  export TWOVAGO_DB_PASSWORD
fi

export TWOVAGO_DB_HOST="${TWOVAGO_DB_HOST:-172.23.176.1}"
export TWOVAGO_DB_PORT="${TWOVAGO_DB_PORT:-3306}"
export TWOVAGO_DB_NAME="${TWOVAGO_DB_NAME:-twovago}"
export TWOVAGO_DB_USER="${TWOVAGO_DB_USER:-twovago_app}"

# Evita probar accidentalmente contra una instancia vieja que use el mismo puerto.
if python3 - "$TEST_PORT" <<'PYPORT'
import socket, sys
port = int(sys.argv[1])
s = socket.socket()
s.settimeout(0.5)
used = s.connect_ex(("127.0.0.1", port)) == 0
s.close()
sys.exit(0 if used else 1)
PYPORT
then
  echo "El puerto $TEST_PORT ya está ocupado. Cierra el backend que lo esté usando o ejecuta: TWOVAGO_TEST_PORT=8084 bash scripts/validar_entregable3.sh"
  exit 1
fi

cd "$ROOT/backend"

echo "========== LIMPIANDO Y COMPILANDO BACKEND ACTUAL =========="
if command -v mvn >/dev/null 2>&1; then
  mvn -q -DskipTests clean package
else
  chmod +x ./mvnw
  ./mvnw -q -DskipTests clean package
fi

JAR="target/backend-0.0.1-SNAPSHOT.jar"
if [[ ! -f "$JAR" ]]; then
  echo "No se encontró el JAR compilado: $JAR"
  exit 1
fi

echo
echo "========== INICIANDO BACKEND NUEVO EN PUERTO $TEST_PORT =========="
java -jar "$JAR" --server.port="$TEST_PORT" --twovago.reservations.expiration-check-ms=2000 >"$LOG_FILE" 2>&1 &
PID="$!"

READY=0
for attempt in $(seq 1 45); do
  if python3 -c "import urllib.request; r=urllib.request.urlopen('http://localhost:${TEST_PORT}/api/health', timeout=2); raise SystemExit(0 if r.status == 200 else 1)" >/dev/null 2>&1; then
    READY=1
    break
  fi
  if ! kill -0 "$PID" 2>/dev/null; then
    break
  fi
  sleep 2
done

if [[ "$READY" -ne 1 ]]; then
  echo "El backend nuevo no inició o no respondió HTTP 200 en /api/health. Registro:"
  tail -n 100 "$LOG_FILE" || true
  exit 1
fi

echo
echo "========== EJECUTANDO PRUEBAS DEL ENTREGABLE 3 =========="
cd "$ROOT"
if TWOVAGO_API_URL="http://localhost:${TEST_PORT}" python3 scripts/pruebas_entregable3.py; then
  STATUS=0
else
  STATUS=$?
fi
echo
echo "Reporte generado: $ROOT/documentacion/reportes/resultados-pruebas-e3.txt"
echo "Registro del backend: $LOG_FILE"
exit "$STATUS"
