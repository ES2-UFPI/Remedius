package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

public class EstoqueUsuarioMedicamentoRequest {

    private Long usuarioId;
    private Long medicamentoId;
    private Integer quantidade;
    private LocalDateTime ultimaCompra;
    private String status;
    private Integer duracao_estimada;

    public EstoqueUsuarioMedicamentoRequest() {
    }

    public EstoqueUsuarioMedicamentoRequest(Long usuarioId, Long medicamentoId, Integer quantidade,
            LocalDateTime ultimaCompra, String status) {
        this.usuarioId = usuarioId;
        this.medicamentoId = medicamentoId;
        this.quantidade = quantidade;
        this.ultimaCompra = ultimaCompra;
        this.status = status;
        this.duracao_estimada = 0;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDateTime ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDuracaoEstimada() {
        return duracao_estimada;
    }

    public void setDuracao_estimada(Integer duracao_estimada) {
        this.duracao_estimada = duracao_estimada;
    }
}