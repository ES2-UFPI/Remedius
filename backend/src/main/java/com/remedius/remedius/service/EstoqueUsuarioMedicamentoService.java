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
            estoque.setDuracaoEstimada(duracaoEstimada);
        }
        return lista_estoquesRepository;
    }

    // Get estoque by ID
    public Optional<EstoqueUsuarioMedicamentoEntity> getEstoqueById(Long id) {
        return estoqueRepository.findById(id);
    }

    // Create Estoque
    public EstoqueUsuarioMedicamentoEntity createEstoque(EstoqueUsuarioMedicamentoRequest estoqueRequest) {
        EstoqueUsuarioMedicamentoEntity estoque = new EstoqueUsuarioMedicamentoEntity();

        // Validar a existência de UsuarioEntity e MedicamentoEntity
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(estoqueRequest.getUsuarioId());
        Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findById(estoqueRequest.getMedicamentoId());

        if (usuarioOptional.isPresent() && medicamentoOptional.isPresent()) {
            estoque.setUsuario(usuarioOptional.get());
            estoque.setMedicamento(medicamentoOptional.get());
            estoque.setQuantidade(estoqueRequest.getQuantidade());
            estoque.setUltimaCompra(estoqueRequest.getUltimaCompra());
            estoque.setStatus(estoqueRequest.getStatus());
            estoque.setDuracaoEstimada(calcularDuracaoEstoque(estoqueRequest.getUsuarioId(), estoqueRequest.getMedicamentoId()));
            return estoqueRepository.save(estoque);
        } else {
            throw new IllegalArgumentException("Usuario or Medicamento does not exist");
        }
    }


    // Update an existing Estoque record
    public Optional<EstoqueUsuarioMedicamentoEntity> updateEstoqueByUserMedicationId(EstoqueUsuarioMedicamentoRequest updatedEstoqueRequest) {
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
                updatedEstoque.setDuracaoEstimada(calcularDuracaoEstoque(updatedEstoqueRequest.getUsuarioId(), updatedEstoqueRequest.getMedicamentoId()));
                return Optional.of(estoqueRepository.save(updatedEstoque));
            } else {
                throw new IllegalArgumentException("Usuario or Medicamento does not exist");
            }
        }

        // Atualizar os campos do estoque existente
        estoqueExistente.setQuantidade(updatedEstoqueRequest.getQuantidade());
        estoqueExistente.setUltimaCompra(updatedEstoqueRequest.getUltimaCompra());
        estoqueExistente.setStatus(updatedEstoqueRequest.getStatus());
        estoqueExistente.setDuracaoEstimada(updatedEstoqueRequest.getDuracaoEstimada());

        return Optional.of(estoqueRepository.save(updatedEstoque));
    }

    // Delete Estoque by ID
    public boolean deleteEstoqueById(Long id) {
        if (estoqueRepository.existsById(id)) {
            estoqueRepository.deleteById(id);
            return true;
        }
        return false;
    }


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
        String aux = frequencia.replace("h", "");
        int horas = Integer.parseInt(aux);
        return 24 / horas;
    }
}