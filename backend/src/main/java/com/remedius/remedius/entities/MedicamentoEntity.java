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
    private int id;

    @Column(name = "nome")
    @NonNull
    private String nome;


    @Column(name = "laboratorio")
    @NonNull
    private String laboratorio;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioMedicamentoEntity> usuarioMedicacoes = new HashSet<>();

    public MedicamentoEntity(String nome, String laboratorio) {
        this.nome = nome;
        this.laboratorio = laboratorio;
    }

    public MedicamentoEntity(){
    }

    public String getNome() {
        return nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "MedicamentoModel , laboratorio=" + laboratorio + ", nome=" + nome
                + "]";
    }
}
