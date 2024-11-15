package com.remedius.remedius.repository;

import com.remedius.remedius.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// classe que vai fazer a comunicação com o banco de dados (com queries)
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {

    // método que retorna um medicamento pelo nome
    @Query("SELECT m FROM MedicamentoEntity m WHERE m.nome = :name")
    Optional<MedicamentoEntity> findByName(@Param("name") String name);

    // método que deleta um medicamento pelo nome
    @Query("DELETE FROM MedicamentoEntity m WHERE m.nome = :name")
    void deleteByName(@Param("name") String name);

    // método que deleta um medicamento pelo ID
    @Query("DELETE FROM MedicamentoEntity m WHERE m.id = :id")
    void deleteById(@Param("id") Long id);
}