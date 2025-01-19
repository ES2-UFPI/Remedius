package com.remedius.remedius.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedius.remedius.DTOs.AtualizarEstoqueDTO;
import com.remedius.remedius.DTOs.CriarEstoqueDTO;
import com.remedius.remedius.entities.UsuarioMedicamentoEstoqueEntity;
import com.remedius.remedius.service.UsuarioMedicamentoEstoqueService;

@RestController
@RequestMapping("/estoque")
public class UsuarioMedicamentoEstoqueController {

    private final UsuarioMedicamentoEstoqueService estoqueService;

    public UsuarioMedicamentoEstoqueController(UsuarioMedicamentoEstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<UsuarioMedicamentoEstoqueEntity> criarEstoque(@RequestBody CriarEstoqueDTO estoque)
            throws NotFoundException {

        UsuarioMedicamentoEstoqueEntity estoqueCriado = estoqueService.criarEstoque(estoque);

        return ResponseEntity.ok(estoqueCriado);

    }

    @PutMapping
    public ResponseEntity<UsuarioMedicamentoEstoqueEntity> atualizarEstoque(@RequestBody AtualizarEstoqueDTO dto)
            throws Exception {

        UsuarioMedicamentoEstoqueEntity estoque = estoqueService.atualizarEstoque(dto);

        return ResponseEntity.ok(estoque);

    }
}
