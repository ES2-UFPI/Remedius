package com.remedius.remedius.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedius.remedius.DTOs.AtualizarTratamentoDTO;
import com.remedius.remedius.DTOs.CriarTratamentoDTO;
import com.remedius.remedius.entities.TratamentoEntity;
import com.remedius.remedius.service.TratamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tratamentos")
@RequiredArgsConstructor
public class TratamentoController {
    private final TratamentoService tratamentoService;

    @PostMapping
    public ResponseEntity<TratamentoEntity> criarTratamento(@RequestBody CriarTratamentoDTO dto) throws Exception {

        TratamentoEntity tratamento = tratamentoService.criarTratamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(tratamento);

    }

    @PutMapping
    public ResponseEntity<TratamentoEntity> atualizarTratamento(@RequestBody AtualizarTratamentoDTO dto)
            throws NotFoundException {
        TratamentoEntity tratamento = tratamentoService.atualizarTratamento(dto);

        return ResponseEntity.ok(tratamento);

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletarTratamento(@PathVariable Long id) throws NotFoundException {
        tratamentoService.deletarTratamento(id);

        return ResponseEntity.noContent().build();

    }
}
