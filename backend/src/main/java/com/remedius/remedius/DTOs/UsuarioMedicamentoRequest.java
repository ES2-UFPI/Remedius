package com.remedius.remedius.DTOs;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UsuarioMedicamentoRequest {
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long medicamentoId;
    private String cor;
}
