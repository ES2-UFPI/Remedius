package com.remedius.remedius.DTOs;

public class MedicamentoRequest {

        private Long medicamentoId;
        private String dataInicial;
        private String frequencia;
    
    
    public MedicamentoRequest(Long medicamentoId, String dataInicial, String frequencia){
        this.medicamentoId = medicamentoId;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
    }

    public Long getMedicamentoId(){
        return this.medicamentoId;
    }

    public String getDataInicial(){
        return this.dataInicial;
    }

    public String getFrequencia(){
        return this.frequencia;
    }

    public void setDataInicial(String dataInicial){
        this.dataInicial = dataInicial;
    }

    public void setFrequencia(String frequencia){
        this.frequencia = frequencia;
    }
}
