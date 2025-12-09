package com.desafio.pagamentos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private String tipo;
    private BigDecimal valor;
    private LocalDate data;
    private LocalDateTime criadoEm;

    public Payment() {}

    public Payment(Long id, String contaOrigem, String contaDestino, String tipo, BigDecimal valor, LocalDate data, LocalDateTime criadoEm) {
        this.id = id;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.criadoEm = criadoEm;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }
    public String getContaDestino() { return contaDestino; }
    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public java.time.LocalDate getData() { return data; }
    public void setData(java.time.LocalDate data) { this.data = data; }
    public java.time.LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(java.time.LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
