package com.hanghe.interfaces.reservation.scheduler;

import com.hanghe.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationScheduler {

    private final ReservationService reservationService;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void expireReservation() {
        reservationService.expireReservation();
    }
}
