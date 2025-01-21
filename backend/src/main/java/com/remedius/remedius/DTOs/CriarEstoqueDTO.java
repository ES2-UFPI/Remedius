package com.remedius.remedius.DTOs;

import java.time.LocalDateTime;

import com.remedius.remedius.enums.StatusEstoque;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarEstoqueDTO {
     private Long usuarioMedicamentoId;

    @Min(0)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private StatusEstoque status;

    private LocalDateTime ultimaCompra;
}
