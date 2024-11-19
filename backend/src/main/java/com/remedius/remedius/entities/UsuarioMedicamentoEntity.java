package com.remedius.remedius.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime dataInicial;

    private LocalDateTime frequencia;

    public UsuarioMedicamentoEntity() {
    }

    public UsuarioMedicamentoEntity(UsuarioEntity usuario, MedicamentoEntity medicamento, LocalDateTime dataInicial, LocalDateTime frequencia) {
        this.usuario = usuario;
        this.medicamento = medicamento;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
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

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getFrequencia() {
        return frequencia;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setMedicamento(MedicamentoEntity medicamento) {
        this.medicamento = medicamento;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setFrequencia(LocalDateTime frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return "UsuarioMedicamentoEntity [dataInicial=" + dataInicial + ", frequencia=" + frequencia + ", id=" + id
                + ", medicamento=" + medicamento + ", usuario=" + usuario + "]";
    }
}
