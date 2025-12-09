package com.desafio.pagamentos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentResponse(
    Long id,
    String contaOrigem,
    String contaDestino,
    String tipo,
    BigDecimal valor,
    LocalDate data,
    LocalDateTime criadoEm
) {}
