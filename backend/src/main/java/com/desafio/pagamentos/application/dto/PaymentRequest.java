package com.desafio.pagamentos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentRequest(
    String contaOrigem,
    String contaDestino,
    String tipo,
    BigDecimal valor,
    LocalDate data
) {}
