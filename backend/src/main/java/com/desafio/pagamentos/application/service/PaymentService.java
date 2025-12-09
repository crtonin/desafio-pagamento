package com.desafio.pagamentos.application.service;

import com.desafio.pagamentos.application.dto.PaymentRequest;
import com.desafio.pagamentos.application.dto.PaymentResponse;
import com.desafio.pagamentos.domain.model.Payment;
import com.desafio.pagamentos.domain.port.PaymentEventPublisherPort;
import com.desafio.pagamentos.domain.port.PaymentRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepositoryPort repository;
    private final PaymentEventPublisherPort publisher;

    public PaymentService(PaymentRepositoryPort repository, PaymentEventPublisherPort publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void submit(PaymentRequest req) {
        Payment p = new Payment();
        p.setContaOrigem(req.contaOrigem());
        p.setContaDestino(req.contaDestino());
        p.setTipo(req.tipo());
        p.setValor(req.valor());
        p.setData(req.data());
        p.setCriadoEm(LocalDateTime.now());
        publisher.publish(p);
    }

    public List<PaymentResponse> listAll() {
        return repository.findAll().stream().map(p -> new PaymentResponse(
            p.getId(),
            p.getContaOrigem(),
            p.getContaDestino(),
            p.getTipo(),
            p.getValor(),
            p.getData(),
            p.getCriadoEm()
        )).collect(Collectors.toList());
    }

    
    public List<PaymentResponse> findByFilters(String tipo, LocalDate inicio, LocalDate fim) {
        return repository.findByFilters(tipo, inicio, fim)
                .stream()
                .map(p -> new PaymentResponse(
                        p.getId(),
                        p.getContaOrigem(),
                        p.getContaDestino(),
                        p.getTipo(),
                        p.getValor(),
                        p.getData(),
                        p.getCriadoEm()
                ))
                .collect(Collectors.toList());
    }
}
