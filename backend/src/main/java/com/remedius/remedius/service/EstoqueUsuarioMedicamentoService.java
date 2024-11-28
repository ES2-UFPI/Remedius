package com.remedius.remedius.service;

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

    // List all Estoque records
    public List<EstoqueUsuarioMedicamentoEntity> getAllEstoques() {
        return EstoqueRepository.findAll();
    }

    // Find Estoque by ID
    public Optional<EstoqueUsuarioMedicamentoEntity> getEstoqueById(Long id) {
        return EstoqueRepository.findById(id);
    }

    // Create a new Estoque record
    public EstoqueUsuarioMedicamentoEntity createEstoque(EstoqueUsuarioMedicamentoEntity Estoque) {
        return EstoqueRepository.save(Estoque);
    }

    // Update an existing Estoque record
    public Optional<EstoqueUsuarioMedicamentoEntity> updateEstoque(Long id, EstoqueUsuarioMedicamentoEntity updatedEstoque) {
        return EstoqueRepository.findById(id).map(Estoque -> {
            Estoque.setQuantidade(updatedEstoque.getQuantidade());
            Estoque.setUltimaCompra(updatedEstoque.getUltimaCompra());
            Estoque.setStatus(updatedEstoque.getStatus());
            return EstoqueRepository.save(Estoque);
        });
    }

    // Delete a Estoque record
    public boolean deleteEstoque(Long id) {
        if (EstoqueRepository.existsById(id)) {
            EstoqueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
