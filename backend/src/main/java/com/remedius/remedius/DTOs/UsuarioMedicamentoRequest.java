package com.remedius.remedius.DTOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

@Data
public class UsuarioMedicamentoRequest {
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long medicamentoId;
    private String cor;
}
