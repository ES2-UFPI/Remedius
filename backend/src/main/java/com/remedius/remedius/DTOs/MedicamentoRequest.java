package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

public class MedicamentoRequest {

    private Long medicamentoId;
    private LocalDateTime dataInicial;
    private String frequencia;
    private Double dosagem;
    private Integer quantidadeInicialEstoque;

    public MedicamentoRequest(Long medicamentoId, LocalDateTime dataInicial, String frequencia, Double dosagem, Integer quantidadeInicialEstoque) {
        this.medicamentoId = medicamentoId;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
        this.dosagem = dosagem;
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

    public Long getMedicamentoId() {
        return this.medicamentoId;
    }

    public LocalDateTime getDataInicial() {
        return this.dataInicial;
    }

    public String getFrequencia() {
        return this.frequencia;
    }

    public double getDosagem() {
        return this.dosagem;
    }

    public Integer getQuantidadeInicialEstoque() {
        return this.quantidadeInicialEstoque;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setDosagem(double dosagem) {
        this.dosagem = dosagem;
    }

    public void setQuantidadeInicialEstoque(Integer quantidadeInicialEstoque) {
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

}
