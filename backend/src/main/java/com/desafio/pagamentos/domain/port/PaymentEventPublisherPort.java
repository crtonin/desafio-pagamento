package com.desafio.pagamentos.domain.port;

import com.desafio.pagamentos.domain.model.Payment;

public interface PaymentEventPublisherPort {
    void publish(Payment payment);
}
