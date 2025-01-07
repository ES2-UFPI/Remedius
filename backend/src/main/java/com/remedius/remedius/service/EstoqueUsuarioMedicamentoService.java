package com.remedius.remedius.service;

import com.remedius.remedius.DTOs.EstoqueUsuarioMedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueUsuarioMedicamentoService {

    @Autowired
    private EstoqueUsuarioMedicamentoRepository estoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    // List all Estoque records
    public List<EstoqueUsuarioMedicamentoEntity> getAllEstoques() {
        return estoqueRepository.findAll();
    }

    // Find Estoque by ID
    public List<EstoqueUsuarioMedicamentoEntity> getEstoqueByUserId(Long id) {
        List<EstoqueUsuarioMedicamentoEntity> lista_estoquesRepository = estoqueRepository.findByUserId(id);
        for (EstoqueUsuarioMedicamentoEntity estoque : lista_estoquesRepository) {
            int duracaoEstimada = calcularDuracaoEstoque(estoque.getUsuario().getId(), estoque.getMedicamento().getId());
            estoque.setDuracao_estimada(duracaoEstimada);
        }
        return lista_estoquesRepository;
    }

    // // Create a new Estoque record
    // public EstoqueUsuarioMedicamentoEntity createEstoque(EstoqueUsuarioMedicamentoEntity Estoque) {
    //     return EstoqueRepository.save(Estoque);
    // }

    // Update an existing Estoque record
    public EstoqueUsuarioMedicamentoEntity updateEstoqueByUserMedicationId(EstoqueUsuarioMedicamentoRequest updatedEstoqueRequest) {
        EstoqueUsuarioMedicamentoEntity estoqueExistente = estoqueRepository.findByUserMedicationId(updatedEstoqueRequest.getUsuarioId(), updatedEstoqueRequest.getMedicamentoId());
        EstoqueUsuarioMedicamentoEntity updatedEstoque = new EstoqueUsuarioMedicamentoEntity();

        if (estoqueExistente == null) {
            // Validar a existência de UsuarioEntity e MedicamentoEntity
            Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(updatedEstoqueRequest.getUsuarioId());
            Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(updatedEstoqueRequest.getMedicamentoId());

            if (usuarioOptional.isPresent() && medicamentoOptional.isPresent()) {
                updatedEstoque.setUsuario(usuarioOptional.get());
                updatedEstoque.setMedicamento(medicamentoOptional.get());
                // Atualizar os campos do estoque existente
                updatedEstoque.setQuantidade(updatedEstoqueRequest.getQuantidade());
                updatedEstoque.setUltimaCompra(updatedEstoqueRequest.getUltimaCompra());
                updatedEstoque.setStatus(updatedEstoqueRequest.getStatus());
                updatedEstoque.setDuracao_estimada(calcularDuracaoEstoque(updatedEstoqueRequest.getUsuarioId(), updatedEstoqueRequest.getMedicamentoId()));
                return estoqueRepository.save(updatedEstoque);
            } else {
                throw new IllegalArgumentException("Usuario or Medicamento does not exist");
            }
        }

        // Atualizar os campos do estoque existente
        estoqueExistente.setQuantidade(updatedEstoqueRequest.getQuantidade());
        estoqueExistente.setUltimaCompra(updatedEstoqueRequest.getUltimaCompra());
        estoqueExistente.setStatus(updatedEstoqueRequest.getStatus());
        estoqueExistente.setDuracao_estimada(updatedEstoqueRequest.getDuracao_estimada());

        return estoqueRepository.save(estoqueExistente);
    }

    // // Delete a Estoque record
    // public boolean deleteEstoque(Long id) {
    //     if (EstoqueRepository.existsById(id)) {
    //         EstoqueRepository.deleteById(id);
    //         return true;
    //     }
    //     return false;
    // }

    // Calcular duração estimada do estoque
    public int calcularDuracaoEstoque(Long usuarioId, Long medicamentoId) {
        // Buscar Estoque
        EstoqueUsuarioMedicamentoEntity estoque = estoqueRepository.findByUserMedicationId(usuarioId, medicamentoId);
        if (estoque == null) {
            throw new IllegalArgumentException("Estoque não encontrado para o usuário e medicamento fornecidos.");
        }

        // Buscar UsuárioMedicamento
        UsuarioMedicamentoEntity usuarioMedicamento = usuarioMedicamentoRepository.findByUsuarioIdAndMedicamentoId(usuarioId, medicamentoId);
        if (usuarioMedicamento == null) {
            throw new IllegalArgumentException("Relação Usuário-Medicamento não encontrada.");
        }

        // Extrair dados necessários
        int quantidade = estoque.getQuantidade();
        double dosagem = usuarioMedicamento.getDosagem();
        String frequencia = usuarioMedicamento.getFrequencia();

        // Converter frequência em doses diárias
        int dosesPorDia = calcularDosesPorDia(frequencia);

        // Calcular consumo diário
        double consumoDiario = dosagem * dosesPorDia;

        System.out.println("Consumo diário: " + consumoDiario);
        System.out.println("Quantidade: " + quantidade);

        // Calcular duração do estoque
        return ((int) Math.floor(quantidade / consumoDiario))+1;
    }

    private int calcularDosesPorDia(String frequencia) {
        if (frequencia.endsWith("h")) {
            int horas = Integer.parseInt(frequencia.replace(".0h", ""));
            return 24 / horas;
        }
        int horas = Integer.parseInt(frequencia.replace(".0", ""));
        return 24 / horas;
    }
}