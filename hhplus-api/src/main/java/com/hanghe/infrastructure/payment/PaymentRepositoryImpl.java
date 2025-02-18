package com.hanghe.infrastructure.payment;

import com.hanghe.domain.payment.entity.Payment;
import com.hanghe.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJPARepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJPARepository.save(payment);
    }

}
