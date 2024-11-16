package com.remedius.remedius.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medicamento")
public class MedicamentoEntity {

    // Atributos do medicamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dosagem", nullable = false)
    private String dosagem;

    @Column(name = "laboratorio", nullable = false)
    private String laboratorio;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @Column(name = "validade", nullable = false)
    private String validade;

    // **Construtor padrão (exigido pelo JPA)** 
    public MedicamentoEntity() {
    }

    // Construtor com argumentos (opcional, para facilitar a criação de objetos)
    public MedicamentoEntity(String nome, String dosagem, String laboratorio, int quantidadeEstoque, String validade) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.laboratorio = laboratorio;
        this.quantidadeEstoque = quantidadeEstoque;
        this.validade = validade;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "MedicamentoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dosagem='" + dosagem + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", validade='" + validade + '\'' +
                '}';
    }
}
