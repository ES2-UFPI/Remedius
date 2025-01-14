package com.remedius.remedius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.remedius.remedius.DTOs.AtualizarTratamentoDTO;
import com.remedius.remedius.DTOs.CriarTratamentoDTO;
import com.remedius.remedius.entities.TratamentoEntity;

public class TratamentoController {

    // private final TratamentoService tratamentoService;

    // @Autowired
    // public TratamentoController(TratamentoService tratamentoService) {

    //     this.tratamentoService = tratamentoService;

    // }


    @PostMapping
    public ResponseEntity<TratamentoEntity> criarTratamento(@RequestBody CriarTratamentoDTO dto) {
        return null;
        // TratamentoEntity tratamento = tratamentoService.criarTratamento(dto);

        // return ResponseEntity.status(HttpStatus.CREATED).body(tratamento);

    }

    @PutMapping

    public ResponseEntity<TratamentoEntity> atualizarTratamento(@RequestBody AtualizarTratamentoDTO dto) {
        return null;
        // Tratamento tratamento = tratamentoService.atualizarTratamento(dto);

        // return ResponseEntity.ok(tratamento);

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletarTratamento(@PathVariable Long id) {
        return null;
        // tratamentoService.deletarTratamento(id);

        // return ResponseEntity.noContent().build();

    }
}
