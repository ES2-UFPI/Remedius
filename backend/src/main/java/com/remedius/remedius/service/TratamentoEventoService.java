package com.remedius.remedius.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO;
import com.remedius.remedius.DTOs.TratamentoEventoUpdateDTO;
import com.remedius.remedius.entities.TratamentoEventoEntity;
import com.remedius.remedius.repository.TratamentoEventoRepository;

@Service
public class TratamentoEventoService {
    
    private final TratamentoEventoRepository tratamentoEventoRepository;
    
    @Autowired
    public TratamentoEventoService(TratamentoEventoRepository tratamentoEventoRepository) {
        this.tratamentoEventoRepository = tratamentoEventoRepository;
    }
    
    public List<EventoAtivoPorUsuarioDTO> buscarEventosAtivos(Long usuarioId, LocalDateTime inicio, LocalDateTime fim) {
        return tratamentoEventoRepository.findEventosAtivosPorUsuario(usuarioId, inicio, fim);
    }
    
    public TratamentoEventoEntity atualizarStatusEvento(TratamentoEventoUpdateDTO dto) {
        TratamentoEventoEntity evento = tratamentoEventoRepository.findByIdWithRelationships(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Evento não encontrado"));
        
        // Verifica se o tratamento ainda está ativo
        if (!evento.getTratamento().getAtivo()) {
            throw new RuntimeException("Não é possível atualizar evento de um tratamento inativo");
        }
        
        // Verifica se o evento é no futuro
        if (evento.getHorario().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível atualizar status de eventos futuros");
        }
        
        evento.setStatus(dto.getStatus());
        
        return tratamentoEventoRepository.save(evento);
    }
}

