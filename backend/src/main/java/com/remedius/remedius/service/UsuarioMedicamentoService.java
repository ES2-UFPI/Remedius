package com.remedius.remedius.service;

import com.remedius.remedius.DTOs.*;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.entities.UsuarioEntity;
import com.remedius.remedius.entities.MedicamentoEntity;
import com.remedius.remedius.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.time.String;
import java.util.List;

//import java.time.String;

// Service
@Service
@RequiredArgsConstructor
public class UsuarioMedicamentoService {
    private final UsuarioMedicamentoRepository usuarioMedicamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MedicamentoRepository medicamentoRepository;

    public UsuarioMedicamentoEntity adicionarMedicamento(UsuarioMedicamentoRequest request) {
        if (usuarioMedicamentoRepository.existsByUsuarioIdAndMedicamentoId(
                request.getUsuarioId(), request.getMedicamentoId())) {
            throw new IllegalStateException("Medicamento já associado a este usuário");
        }

        UsuarioEntity usuario = usuarioRepository.findById(request.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
        MedicamentoEntity medicamento = medicamentoRepository.findById(request.getMedicamentoId())
            .orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));

        UsuarioMedicamentoEntity usuarioMedicamento = new UsuarioMedicamentoEntity();
        usuarioMedicamento.setUsuario(usuario);
        usuarioMedicamento.setMedicamento(medicamento);
        usuarioMedicamento.setCor(request.getCor());

        return usuarioMedicamentoRepository.save(usuarioMedicamento);
    }

    public UsuarioMedicamentoEntity atualizarMedicamento(Long id, UsuarioMedicamentoRequest request) {
        UsuarioMedicamentoEntity usuarioMedicamento = usuarioMedicamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Relação usuário-medicamento não encontrada"));

        // Se houver mudança de usuário ou medicamento, verifica se já existe a relação
        if (!usuarioMedicamento.getUsuario().getId().equals(request.getUsuarioId()) ||
            !usuarioMedicamento.getMedicamento().getId().equals(request.getMedicamentoId())) {
            
            if (usuarioMedicamentoRepository.existsByUsuarioIdAndMedicamentoId(
                    request.getUsuarioId(), request.getMedicamentoId())) {
                throw new IllegalStateException("Medicamento já associado a este usuário");
            }

            UsuarioEntity novoUsuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            MedicamentoEntity novoMedicamento = medicamentoRepository.findById(request.getMedicamentoId())
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));

            usuarioMedicamento.setUsuario(novoUsuario);
            usuarioMedicamento.setMedicamento(novoMedicamento);
        }

        usuarioMedicamento.setCor(request.getCor());
        return usuarioMedicamentoRepository.save(usuarioMedicamento);
    }

    public void deletarMedicamento(Long id) {
        if (!usuarioMedicamentoRepository.existsById(id)) {
            throw new RuntimeException("Relação usuário-medicamento não encontrada");
        }
        usuarioMedicamentoRepository.deleteById(id);
    }
}