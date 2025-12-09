package com.desafio.pagamentos.infrastructure.repository;

import com.desafio.pagamentos.domain.model.Payment;
import com.desafio.pagamentos.domain.port.PaymentRepositoryPort;
import com.desafio.pagamentos.infrastructure.repository.entity.PaymentEntity;
import com.desafio.pagamentos.infrastructure.repository.jpa.SpringPaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringPaymentJpaRepository jpa;

    public PaymentRepositoryAdapter(SpringPaymentJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity e = toEntity(payment);
        PaymentEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public java.util.List<Payment> findAll() {
        return jpa.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

       @Override
    public List<Payment> findByFilters(String tipo, LocalDate inicio, LocalDate fim) {
        return jpa.findByFilters(tipo, inicio, fim)
                .stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    private PaymentEntity toEntity(Payment p) {
        PaymentEntity e = new PaymentEntity();
        e.setId(p.getId());
        e.setContaOrigem(p.getContaOrigem());
        e.setContaDestino(p.getContaDestino());
        e.setTipo(p.getTipo());
        e.setValor(p.getValor());
        e.setData(p.getData());
        e.setCriadoEm(p.getCriadoEm());
        return e;
    }

    private Payment toDomain(PaymentEntity e) {
        Payment p = new Payment();
        p.setId(e.getId());
        p.setContaOrigem(e.getContaOrigem());
        p.setContaDestino(e.getContaDestino());
        p.setTipo(e.getTipo());
        p.setValor(e.getValor());
        p.setData(e.getData());
        p.setCriadoEm(e.getCriadoEm());
        return p;
    }
}
