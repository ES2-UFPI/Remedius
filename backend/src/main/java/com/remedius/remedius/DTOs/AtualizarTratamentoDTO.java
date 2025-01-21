package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarTratamentoDTO {
    private Long id;

    private LocalDateTime dataInicial;

    private Integer frequencia;

    private Integer duracao;

    private Double dosagem;

    private String observacao;

    private Boolean ativo;
}
