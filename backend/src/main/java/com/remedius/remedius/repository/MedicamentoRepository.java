package com.remedius.remedius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remedius.remedius.entities.MedicamentoEntity;

// classe que vai fazer a comunicação com o banco de dados (com queries)
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long>{
    
}
