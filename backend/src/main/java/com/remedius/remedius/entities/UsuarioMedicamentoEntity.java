package com.remedius.remedius.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario_medicamentos")
public class UsuarioMedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private MedicamentoEntity medicamento;

    private String cor;

    @OneToOne(mappedBy = "usuarioMedicamento")
    @JsonIgnore
    private UsuarioMedicamentoEstoqueEntity estoque;

    @OneToMany(mappedBy = "usuarioMedicamento")
    private List<TratamentoEntity> tratamentos;
}
