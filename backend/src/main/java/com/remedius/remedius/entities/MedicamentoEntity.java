package com.remedius.remedius.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "medicamentos")
public class MedicamentoEntity {
    // classe com os atributos do medicamento: nome, dosagem, laboratório, quantidade em estoque, validade
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String dosagem;
    private String laboratorio;
    private int quantidadeEstoque;
    private String validade;

    public MedicamentoEntity(String nome, String dosagem, String laboratorio, int quantidadeEstoque, String validade) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.laboratorio = laboratorio;
        this.quantidadeEstoque = quantidadeEstoque;
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getValidade() {
        return validade;
    }  

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "MedicamentoModel [dosagem=" + dosagem + ", laboratorio=" + laboratorio + ", nome=" + nome
                + ", quantidadeEstoque=" + quantidadeEstoque + ", validade=" + validade + "]";
    }
}
