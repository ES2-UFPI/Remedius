package com.remedius.remedius.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.remedius.remedius.DTOs.AtualizarTratamentoDTO;
import com.remedius.remedius.DTOs.CriarTratamentoDTO;
import com.remedius.remedius.entities.TratamentoEntity;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.repository.TratamentoRepository;
import com.remedius.remedius.repository.UsuarioMedicamentoRepository;

import jakarta.transaction.Transactional;

public class TratamentoService {
    private final TratamentoRepository tratamentoRepository;

    private final UsuarioMedicamentoRepository usuarioMedicamentoRepository;
    // TODO: Adicionar Evento Repository

    @Autowired

    public TratamentoService(TratamentoRepository tratamentoRepository,
            UsuarioMedicamentoRepository usuarioMedicamentoRepository) {

        this.tratamentoRepository = tratamentoRepository;

        this.usuarioMedicamentoRepository = usuarioMedicamentoRepository;

    }

    @Transactional
    public TratamentoEntity criarTratamento(CriarTratamentoDTO dto) throws Exception {

        // Verifica se existe a relação usuário-medicamento

        UsuarioMedicamentoEntity usuarioMedicamento = usuarioMedicamentoRepository
                .findById(dto.getUsuarioMedicamentoId())
                .orElseThrow(() -> new NotFoundException());

        // Verifica se já existe um tratamento ativo para este usuário-medicamento

        if (tratamentoRepository.existsByUsuarioMedicamentoIdAndAtivoTrue(dto.getUsuarioMedicamentoId())) {

            throw new Exception("Já existe um tratamento ativo para este medicamento");

        }

        TratamentoEntity tratamento = new TratamentoEntity();

        tratamento.setUsuarioMedicamento(usuarioMedicamento);

        tratamento.setDataInicial(dto.getDataInicial());

        tratamento.setFrequencia(dto.getFrequencia());

        tratamento.setDuracao(dto.getDuracao());

        tratamento.setDosagem(dto.getDosagem());

        tratamento.setObservacao(dto.getObservacao());

        tratamento.setAtivo(true);

        tratamento = tratamentoRepository.save(tratamento);

        // Gera eventos do tratamento

        gerarEventosTratamento(tratamento);

        return tratamento;

    }

    @Transactional
    public TratamentoEntity atualizarTratamento(AtualizarTratamentoDTO dto) throws NotFoundException {

        TratamentoEntity tratamento = tratamentoRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException());

        boolean recalcularEventos = false;

        if (dto.getDataInicial() != null) {

            tratamento.setDataInicial(dto.getDataInicial());

            recalcularEventos = true;

        }

        if (dto.getFrequencia() != null) {

            tratamento.setFrequencia(dto.getFrequencia());

            recalcularEventos = true;

        }

        if (dto.getDuracao() != null) {

            tratamento.setDuracao(dto.getDuracao());

            recalcularEventos = true;

        }

        if (dto.getDosagem() != null) {

            tratamento.setDosagem(dto.getDosagem());

        }

        if (dto.getObservacao() != null) {

            tratamento.setObservacao(dto.getObservacao());

        }

        if (dto.getAtivo() != null) {

            tratamento.setAtivo(dto.getAtivo());

        }

        tratamento = tratamentoRepository.save(tratamento);

        // Se houve alteração em parâmetros que afetam os eventos, recalcula-os
        // TODO: Implementar método para gerar eventos de tratamento
        if (recalcularEventos) {

            // tratamentoEventoRepository.deleteByTratamentoId(tratamento.getId());

            gerarEventosTratamento(tratamento);

        }

        return tratamento;

    }

    @Transactional
    public void deletarTratamento(Long id) throws NotFoundException {

        if (!tratamentoRepository.existsById(id)) {

            throw new NotFoundException();

        }

        // Primeiro deleta os eventos associados
        // TODO: Implementar método para deletar eventos de tratamento
        // tratamentoEventoRepository.deleteByTratamentoId(id);

        // Depois deleta o tratamento

        tratamentoRepository.deleteById(id);

    }

    // TODO: Implementar método para gerar eventos de tratamento
    private void gerarEventosTratamento(TratamentoEntity tratamento) {

        // LocalDateTime dataHoraAtual = tratamento.getDataInicial();

        // int eventosGerados = 0;

        // int horasTotal = tratamento.getDuracao() * 24; // Converte duração de dias
        // para horas

        // int intervaloHoras = 24 / tratamento.getFrequencia(); // Calcula intervalo em
        // horas entre eventos

        // while (eventosGerados < (horasTotal / intervaloHoras)) {

        // TratamentoEvento evento = new TratamentoEvento();

        // evento.setTratamento(tratamento);

        // evento.setHorario(dataHoraAtual);

        // evento.setStatus(StatusEvento.TOMADO); // Status inicial

        // tratamentoEventoRepository.save(evento);

        // dataHoraAtual = dataHoraAtual.plusHours(intervaloHoras);

        // eventosGerados++;

        // }

    }
}
