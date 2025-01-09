package com.hanghe.domain.payment.repository;

import com.hanghe.domain.payment.emtity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
