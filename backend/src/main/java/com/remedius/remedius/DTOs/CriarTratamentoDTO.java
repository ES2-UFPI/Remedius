package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CriarTratamentoDTO {

    private Long usuarioMedicamentoId;

    private LocalDateTime dataInicial;

    private Integer frequencia;

    private Integer duracao;

    private Double dosagem;

    private String observacao; // Opcional
}
