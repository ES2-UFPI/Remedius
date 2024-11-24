package com.remedius.remedius.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_medicacoes")
public class UsuarioMedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "medicacao_id", nullable = false)
    private MedicamentoEntity medicamento;

    private String dataInicial;

    private String frequencia;


    public UsuarioMedicamentoEntity(UsuarioEntity usuario, MedicamentoEntity medicamento, String dataInicial, String frequencia) {
        this.usuario = usuario;
        this.medicamento = medicamento;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
    }

    public UsuarioMedicamentoEntity() {
    }

    // getId da relação 
    public Integer getId() {
        return id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public MedicamentoEntity getMedicamento() {
        return medicamento;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setMedicamento(MedicamentoEntity medicamento) {
        this.medicamento = medicamento;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return "UsuarioMedicamentoEntity [dataInicial=" + dataInicial + ", frequencia=" + frequencia + ", id=" + id
                + ", medicamento=" + medicamento + ", usuario=" + usuario + "]";
    }
}
