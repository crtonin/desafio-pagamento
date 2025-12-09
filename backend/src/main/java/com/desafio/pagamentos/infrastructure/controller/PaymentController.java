package com.desafio.pagamentos.infrastructure.controller;

import com.desafio.pagamentos.application.dto.PaymentRequest;
import com.desafio.pagamentos.application.dto.PaymentResponse;
import com.desafio.pagamentos.application.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Validated
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/enqueue")
    public ResponseEntity<Void> enqueue(@Valid @RequestBody PaymentRequest req) {
        service.submit(req);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PaymentResponse>> findByFilters(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        return ResponseEntity.ok(service.findByFilters(tipo, inicio, fim));
    }
}
