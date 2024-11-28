package com.remedius.remedius.entities;
import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
@Table(name = "medicamento")
public class MedicamentoEntity {
    // classe com os atributos do medicamento: nome, dosagem, laboratório, quantidade em estoque, validade

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NonNull
    private String nome;


    @Column(name = "laboratorio")
    @NonNull
    private String laboratorio;

    @Column(name = "cor")
    @NonNull
    private String cor;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioMedicamentoEntity> usuarioMedicacoes = new HashSet<>();

    public MedicamentoEntity(String nome, String laboratorio) {
        this.nome = nome;
        this.laboratorio = laboratorio;
    }


    public MedicamentoEntity(){
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getCor() {
        return cor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "MedicamentoModel , laboratorio=" + laboratorio + ", nome=" + nome + ", cor" + cor
                + "]";
    }
}
