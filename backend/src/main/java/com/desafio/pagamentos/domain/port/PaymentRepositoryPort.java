package com.desafio.pagamentos.domain.port;

import com.desafio.pagamentos.domain.model.Payment;
import java.util.List;
import java.time.LocalDate;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    List<Payment> findAll();
    List<Payment> findByFilters(String tipo, LocalDate inicio, LocalDate fim);
}
