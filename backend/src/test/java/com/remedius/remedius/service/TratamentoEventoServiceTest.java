package com.remedius.remedius.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO;
import com.remedius.remedius.DTOs.TratamentoEventoUpdateDTO;
import com.remedius.remedius.entities.TratamentoEntity;
import com.remedius.remedius.entities.TratamentoEventoEntity;
import com.remedius.remedius.enums.StatusEventoEnum;
import com.remedius.remedius.repository.TratamentoEventoRepository;

@ExtendWith(MockitoExtension.class)
public class TratamentoEventoServiceTest {

    @Mock
    private TratamentoEventoRepository tratamentoEventoRepository;

    @InjectMocks
    private TratamentoEventoService tratamentoEventoService;

    @BeforeEach
    public void setUp() {
        // Configurações necessárias antes de cada teste
    }

    @Test
    public void testBuscarEventosAtivos() {
        Long usuarioId = 1L;
        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fim = LocalDateTime.now().plusDays(1);
        List<EventoAtivoPorUsuarioDTO> eventosMock = List.of(new EventoAtivoPorUsuarioDTO());

        when(tratamentoEventoRepository.findEventosAtivosPorUsuario(usuarioId, inicio, fim))
            .thenReturn(eventosMock);

        List<EventoAtivoPorUsuarioDTO> eventos = tratamentoEventoService.buscarEventosAtivos(usuarioId, inicio, fim);

        assertNotNull(eventos);
        assertEquals(eventosMock.size(), eventos.size());
        verify(tratamentoEventoRepository, times(1)).findEventosAtivosPorUsuario(usuarioId, inicio, fim);
    }

    @Test
    public void testBuscarEventosAtrasados() {
        Long usuarioId = 1L;
        List<EventoAtivoPorUsuarioDTO> eventosMock = List.of(new EventoAtivoPorUsuarioDTO());

        when(tratamentoEventoRepository.findEventosAtrasadosPorUsuario(eq(usuarioId), any(LocalDateTime.class)))
            .thenReturn(eventosMock);

        List<EventoAtivoPorUsuarioDTO> eventos = tratamentoEventoService.buscarEventosAtrasados(usuarioId);

        assertNotNull(eventos);
        assertEquals(eventosMock.size(), eventos.size());
        verify(tratamentoEventoRepository, times(1)).findEventosAtrasadosPorUsuario(eq(usuarioId), any(LocalDateTime.class));
    }

    @Test
    public void testAtualizarStatusEvento() {
        long eventoId = 1L;
        TratamentoEventoUpdateDTO dto = new TratamentoEventoUpdateDTO(eventoId, StatusEventoEnum.TOMADO);

        TratamentoEventoEntity eventoMock = mock(TratamentoEventoEntity.class);
        TratamentoEntity tratamentoMock = mock(TratamentoEntity.class);

        when(tratamentoEventoRepository.findByIdWithRelationships(eventoId))
            .thenReturn(Optional.of(eventoMock));
        when(eventoMock.getTratamento()).thenReturn(tratamentoMock);
        when(tratamentoMock.getAtivo()).thenReturn(true);
        when(eventoMock.getHorario()).thenReturn(LocalDateTime.now().minusDays(1));
        when(tratamentoEventoRepository.save(eventoMock)).thenReturn(eventoMock);

        TratamentoEventoEntity resultado = tratamentoEventoService.atualizarStatusEvento(dto);

        assertNotNull(resultado);
        verify(eventoMock, times(1)).setStatus(StatusEventoEnum.valueOf("NOVO_STATUS"));
        verify(tratamentoEventoRepository, times(1)).save(eventoMock);
    }

    @Test
    public void testAtualizarStatusEventoThrowsNoSuchElementException() {
        long eventoId = 1L;
        TratamentoEventoUpdateDTO dto = new TratamentoEventoUpdateDTO(eventoId, StatusEventoEnum.TOMADO);

        when(tratamentoEventoRepository.findByIdWithRelationships(eventoId))
            .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            tratamentoEventoService.atualizarStatusEvento(dto);
        });
    }

    @Test
    public void testAtualizarStatusEventoThrowsRuntimeExceptionTratamentoInativo() {
        long eventoId = 1L;
        TratamentoEventoUpdateDTO dto = new TratamentoEventoUpdateDTO(eventoId, StatusEventoEnum.TOMADO);

        TratamentoEventoEntity eventoMock = mock(TratamentoEventoEntity.class);
        TratamentoEntity tratamentoMock = mock(TratamentoEntity.class);

        when(tratamentoEventoRepository.findByIdWithRelationships(eventoId))
            .thenReturn(Optional.of(eventoMock));
        when(eventoMock.getTratamento()).thenReturn(tratamentoMock);
        when(tratamentoMock.getAtivo()).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tratamentoEventoService.atualizarStatusEvento(dto);
        });

        assertEquals("Não é possível atualizar evento de um tratamento inativo", exception.getMessage());
    }

    @Test
    public void testAtualizarStatusEventoThrowsRuntimeExceptionEventoFuturo() {
        long eventoId = 1L;
        TratamentoEventoUpdateDTO dto = new TratamentoEventoUpdateDTO(eventoId, StatusEventoEnum.TOMADO);

        TratamentoEventoEntity eventoMock = mock(TratamentoEventoEntity.class);
        TratamentoEntity tratamentoMock = mock(TratamentoEntity.class);

        when(tratamentoEventoRepository.findByIdWithRelationships(eventoId))
            .thenReturn(Optional.of(eventoMock));
        when(eventoMock.getTratamento()).thenReturn(tratamentoMock);
        when(tratamentoMock.getAtivo()).thenReturn(true);
        when(eventoMock.getHorario()).thenReturn(LocalDateTime.now().plusDays(1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tratamentoEventoService.atualizarStatusEvento(dto);
        });

        assertEquals("Não é possível atualizar status de eventos futuros", exception.getMessage());
    }
}
