package com.remedius.remedius.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO;
import com.remedius.remedius.DTOs.TratamentoEventoUpdateDTO;
import com.remedius.remedius.entities.TratamentoEventoEntity;
import com.remedius.remedius.service.TratamentoEventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tratamento-eventos")
public class TratamentoEventoController {
    
    private final TratamentoEventoService tratamentoEventoService;
    
    @Autowired
    public TratamentoEventoController(TratamentoEventoService tratamentoEventoService) {
        this.tratamentoEventoService = tratamentoEventoService;
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EventoAtivoPorUsuarioDTO>> buscarEventosAtivos(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        
        List<EventoAtivoPorUsuarioDTO> eventos = tratamentoEventoService.buscarEventosAtivos(usuarioId, inicio, fim);
        return ResponseEntity.ok(eventos);
    }
    
    @PutMapping("/status")
    public ResponseEntity<TratamentoEventoEntity> atualizarStatusEvento(@RequestBody @Valid TratamentoEventoUpdateDTO dto) {
        TratamentoEventoEntity evento = tratamentoEventoService.atualizarStatusEvento(dto);
        return ResponseEntity.ok(evento);
    }
}
