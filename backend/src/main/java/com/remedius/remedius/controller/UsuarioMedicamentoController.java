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


    @GetMapping
    public ResponseEntity<Iterable<UsuarioMedicamentoEntity>> listarMedicamentos() {
        return ResponseEntity.ok(usuarioMedicamentoService.listarMedicamentos());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Iterable<UsuarioMedicamentoEntity>> listarMedicamentosUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok(usuarioMedicamentoService.listarMedicamentosUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<UsuarioMedicamentoEntity> adicionarMedicamento(
            @RequestBody @Valid UsuarioMedicamentoRequest request) {
        return ResponseEntity.ok(usuarioMedicamentoService.adicionarMedicamento(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioMedicamentoEntity> atualizarMedicamento(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioMedicamentoRequest request) {
                //TODO : Criar um DTO pro Update
        return ResponseEntity.ok(usuarioMedicamentoService.atualizarMedicamento(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        usuarioMedicamentoService.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}
