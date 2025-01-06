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
    private EstoqueUsuarioMedicamentoRepository EstoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // List all Estoque records
    public List<EstoqueUsuarioMedicamentoEntity> getAllEstoques() {
        return EstoqueRepository.findAll();
    }

    // Find Estoque by ID
    public List<EstoqueUsuarioMedicamentoEntity> getEstoqueByUserId(Long id) {
        return EstoqueRepository.findByUserId(id);
    }

    // // Create a new Estoque record
    // public EstoqueUsuarioMedicamentoEntity createEstoque(EstoqueUsuarioMedicamentoEntity Estoque) {
    //     return EstoqueRepository.save(Estoque);
    // }

    // Update an existing Estoque record
    public EstoqueUsuarioMedicamentoEntity updateEstoqueByMedicationId(Long id, EstoqueUsuarioMedicamentoRequest updatedEstoqueRequest) {
    EstoqueUsuarioMedicamentoEntity estoqueExistente = EstoqueRepository.findByMedicationId(id);
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
            return EstoqueRepository.save(updatedEstoque);
        } else {
            throw new IllegalArgumentException("Usuario or Medicamento does not exist");
        }
    }

    // Atualizar os campos do estoque existente
    estoqueExistente.setQuantidade(updatedEstoqueRequest.getQuantidade());
    estoqueExistente.setUltimaCompra(updatedEstoqueRequest.getUltimaCompra());
    estoqueExistente.setStatus(updatedEstoqueRequest.getStatus());

    return EstoqueRepository.save(estoqueExistente);
}

    // // Delete a Estoque record
    // public boolean deleteEstoque(Long id) {
    //     if (EstoqueRepository.existsById(id)) {
    //         EstoqueRepository.deleteById(id);
    //         return true;
    //     }
    //     return false;
    // }
}
