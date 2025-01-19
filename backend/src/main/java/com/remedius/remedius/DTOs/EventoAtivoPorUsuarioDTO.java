package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

import com.remedius.remedius.enums.StatusEventoEnum;

import lombok.Data;

@Data
public class EventoAtivoPorUsuarioDTO {
    private Long eventoId;
    private String medicamentoNome;
    private String laboratorio;
    private LocalDateTime horario;
    private Double dosagem;
    private String cor;
    private StatusEventoEnum status;
}