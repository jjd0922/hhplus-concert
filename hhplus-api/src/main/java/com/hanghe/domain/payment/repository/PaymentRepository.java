package com.hanghe.domain.payment.repository;

import com.hanghe.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository {
    Payment save(Payment payment);
}
