package com.remedius.remedius.service;


import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.MedicamentoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicamentoService {
    // quero gerar requisições para o backend
    // requisições: GET, POST, PUT, DELETE
    // gere o codigo para cada uma dessas requisições
    // getAllMedicamentos
    // getMedicamentoByName
    // createMedicamento
    // updateMedicamento
    // deleteMedicamento

    private final MedicamentoRepository medicamentoRepository;

    /*
     * @AutoWired:
     * o Spring irá localizar, instanciar e "injetar" um objeto necessário em um campo,
     * construtor ou método de uma classe, sem que você precise criar explicitamente uma
     * nova instância desse objeto.
     */
    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }


    public List<MedicamentoEntity> createMedicamento(MedicamentoEntity medicamento) {
        medicamentoRepository.save(medicamento);
        return getAllMedicamentos();
    }


    public List<MedicamentoEntity> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }


    public List<MedicamentoEntity> updateMedicamento(MedicamentoEntity medicamento) {
        medicamentoRepository.save(medicamento);
        return getAllMedicamentos();
    }


    public List<MedicamentoEntity> deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
        return getAllMedicamentos();
    }


    /*public List<MedicamentoEntity> getMedicamento(String name) {
        return getAllMedicamentos();
    }

    
    public MedicamentoEntity getMedicamentoById(Long id) {
        return null;
    }

    public MedicamentoEntity updateMedicamentoById(Long id, MedicamentoEntity medicamento) {
        return null;
    }

    public List<MedicamentoEntity> deleteMedicamentoById(Long id) {
        return getAllMedicamentos();
    }*/
}
