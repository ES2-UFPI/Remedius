package com.remedius.remedius.DTOs;

import com.remedius.remedius.enums.StatusEventoEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TratamentoEventoUpdateDTO {
    @NotNull
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEventoEnum status;
}
