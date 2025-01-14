package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor
public class CriarTratamentoDTO {

    private Long usuarioMedicamentoId;

    private LocalDateTime dataInicial;

    private Integer frequencia;

    private Integer duracao;

    private Double dosagem;

    private String observacao; //Opcional
}
