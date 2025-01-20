package com.remedius.remedius.entities;

import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.remedius.remedius.enums.StatusEstoque;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity

@Table(name = "medicamento_estoques")

public class UsuarioMedicamentoEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_medicamento_id", unique = true)
    private UsuarioMedicamentoEntity usuarioMedicamento;

    private Integer quantidade;

    private LocalDateTime ultimaCompra;

    @Enumerated(EnumType.STRING)

    private StatusEstoque status;

}
