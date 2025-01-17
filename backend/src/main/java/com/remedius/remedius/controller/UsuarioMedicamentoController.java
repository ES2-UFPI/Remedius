package com.remedius.remedius.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.remedius.remedius.DTOs.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/usuario-medicamentos")
@RequiredArgsConstructor
public class UsuarioMedicamentoController {
    private final UsuarioMedicamentoService usuarioMedicamentoService;

    @PostMapping
    public ResponseEntity<UsuarioMedicamentoEntity> adicionarMedicamento(
            @RequestBody @Valid UsuarioMedicamentoRequest request) {
        return ResponseEntity.ok(usuarioMedicamentoService.adicionarMedicamento(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioMedicamentoEntity> atualizarMedicamento(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioMedicamentoRequest request) {
        return ResponseEntity.ok(usuarioMedicamentoService.atualizarMedicamento(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        usuarioMedicamentoService.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}
