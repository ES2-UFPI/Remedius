package com.remedius.remedius.service;


import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicamentoService {
    // quero gerar requisições para o backend -> interage com a classe MedicamentoRepository para fazer as queries (CRUD)

    // requisições: GET, POST, PUT, DELETE
    // gere o codigo para cada uma dessas requisições

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<MedicamentoEntity> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    // Retorna um medicamento pelo nome
    public MedicamentoEntity getMedicamentoByName(String name) {
        Optional<MedicamentoEntity> medicamento = medicamentoRepository.findByName(name);
        return medicamento.orElse(null);
    }

    // Cria um novo medicamento
    public MedicamentoEntity createMedicamento(MedicamentoEntity medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    // Deleta um medicamento pelo nome
    public void deleteMedicamentoByName(String name) {
        Optional<MedicamentoEntity> medicamento = medicamentoRepository.findByName(name);
        medicamento.ifPresent(medicamentoRepository::delete);
    }

    // Retorna um medicamento pelo ID
    public MedicamentoEntity getMedicamentoById(Long id) {
        Optional<MedicamentoEntity> medicamento = medicamentoRepository.findById(id);
        return medicamento.orElse(null);
    }

    // Atualiza um medicamento existente pelo ID
    public MedicamentoEntity updateMedicamentoById(Long id, MedicamentoEntity medicamento) {
        Optional<MedicamentoEntity> existingMedicamento = medicamentoRepository.findById(id);
        if (existingMedicamento.isPresent()) {
            MedicamentoEntity updatedMedicamento = existingMedicamento.get();
            updatedMedicamento.setNome(medicamento.getNome());
            updatedMedicamento.setDosagem(medicamento.getDosagem());
            updatedMedicamento.setLaboratorio(medicamento.getLaboratorio());
            updatedMedicamento.setQuantidadeEstoque(medicamento.getQuantidadeEstoque());
            updatedMedicamento.setValidade(medicamento.getValidade());

            return medicamentoRepository.save(updatedMedicamento);
        } else {
            return null;
        }
    }

    // Deleta um medicamento pelo ID
    public void deleteMedicamentoById(Long id) {
        Optional<MedicamentoEntity> medicamento = medicamentoRepository.findById(id);
        medicamento.ifPresent(medicamentoRepository::delete); // o que o 2 pontos faz?
    }
}