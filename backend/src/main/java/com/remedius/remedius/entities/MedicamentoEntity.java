package com.remedius.remedius.entities;
import jakarta.persistence.Entity;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
@Table(name = "medicamento")
public class MedicamentoEntity {
    // classe com os atributos do medicamento: nome, dosagem, laboratório, quantidade em estoque, validade

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    @NonNull
    private String nome;

    @Column(name = "dosagem")
    @NonNull
    private String dosagem;

    @Column(name = "laboratorio")
    @NonNull
    private String laboratorio;

    @Column(name = "quantidade_estoque")
    @NonNull
    private int quantidadeEstoque;

    @Column(name = "validade")
    @NonNull
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
