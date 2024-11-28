package com.remedius.remedius.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "usuario_medicacoes")
@AllArgsConstructor
public class UsuarioMedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "medicacao_id", nullable = false)
    private MedicamentoEntity medicamento;

    private LocalDateTime dataInicial;

    private String frequencia;

    private Double dosagem;

    private Integer quantidadeInicialEstoque;

    public UsuarioMedicamentoEntity(UsuarioEntity usuario, MedicamentoEntity medicamento, LocalDateTime dataInicial,
            String frequencia, Double dosagem, Integer quantidadeInicialEstoque) {
        this.usuario = usuario;
        this.medicamento = medicamento;
        this.dataInicial = dataInicial;
        this.frequencia = frequencia;
        this.dosagem = dosagem;
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

    public UsuarioMedicamentoEntity() {
    }

    // getId da relação
    public Long getId() {
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

    public String getFrequencia() {
        return frequencia;
    }

    public double getDosagem() {
        return dosagem;
    }

    public Integer getQuantidadeInicialEstoque() {
        return quantidadeInicialEstoque;
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

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setDosagem(double dosagem) {
        this.dosagem = dosagem;
    }

    public void setQuantidadeInicialEstoque(Integer quantidadeInicialEstoque) {
        this.quantidadeInicialEstoque = quantidadeInicialEstoque;
    }

    @Override
    public String toString() {
        return "UsuarioMedicamentoEntity [dataInicial=" + dataInicial + ", dosagem=" + dosagem + ", frequencia="
                + frequencia + ", id=" + id + ", medicamento=" + medicamento + ", quantidadeInicialEstoque="
                + quantidadeInicialEstoque + ", usuario=" + usuario + "]";
    }
}
