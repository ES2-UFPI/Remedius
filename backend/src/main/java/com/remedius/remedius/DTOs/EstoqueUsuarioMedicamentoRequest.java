package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

public class EstoqueUsuarioMedicamentoRequest {

    private Long usuarioId;
    private Long medicamentoId;
    private int quantidade;
    private LocalDateTime ultimaCompra;
    private String status;

    public EstoqueUsuarioMedicamentoRequest() {
    }

    public EstoqueUsuarioMedicamentoRequest(Long usuarioId, Long medicamentoId, int quantidade, LocalDateTime ultimaCompra, String status) {
        this.usuarioId = usuarioId;
        this.medicamentoId = medicamentoId;
        this.quantidade = quantidade;
        this.ultimaCompra = ultimaCompra;
        this.status = status;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
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
}
