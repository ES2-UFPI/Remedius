package com.remedius.remedius.DTOs;

//import java.time.String;

public class MedicamentoRequest {

    private Long medicamentoId;
    private String dataInicial;
    private String frequencia;
    private Double dosagem;
    private Integer quantidadeInicialEstoque;

    public MedicamentoRequest(Long medicamentoId, String dataInicial, String frequencia, Double dosagem, Integer quantidadeInicialEstoque) {
        this.medicamentoId = medicamentoId;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
        this.dosagem = dosagem;
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

    public MedicamentoRequest() {
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

    public Double getDosagem() {
        return this.dosagem;
    }

    public Integer getQuantidadeInicialEstoque() {
        return this.quantidadeInicialEstoque;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
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

    public void setQuantidadeInicialEstoque(Integer quantidadeInicialEstoque) {
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

}
