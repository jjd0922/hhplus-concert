package com.hanghe.infrastructure.payment;

import com.hanghe.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment,Long> {
}
