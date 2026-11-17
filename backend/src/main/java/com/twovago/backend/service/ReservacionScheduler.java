package com.twovago.backend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservacionScheduler {

    private final ReservacionService reservacionService;

    public ReservacionScheduler(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    @Scheduled(fixedDelayString = "${twovago.reservations.expiration-check-ms:30000}")
    public void revisarReservaciones() {
        reservacionService.actualizarEstados();
    }
}
