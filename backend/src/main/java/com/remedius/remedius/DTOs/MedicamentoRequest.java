package com.remedius.remedius.DTOs;

public class MedicamentoRequest {

    private Long medicamentoId;
    private String dataInicial;
    private String frequencia;
    private double dosagem;

    public MedicamentoRequest(Long medicamentoId, String dataInicial, String frequencia, double dosagem) {
        this.medicamentoId = medicamentoId;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
        this.dosagem = dosagem;
    }

    public Long getMedicamentoId() {
        return this.medicamentoId;
    }

    public String getDataInicial() {
        return this.dataInicial;
    }

    public String getFrequencia() {
        return this.frequencia;
    }

    public double getDosagem() {
        return this.dosagem;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setDosagem(double dosagem) {
        this.dosagem = dosagem;
    }

}
