package com.remedius.remedius.repository;

import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueUsuarioMedicamentoRepository extends JpaRepository<EstoqueUsuarioMedicamentoEntity, Integer> {

    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.usuarioId = :usuarioId")
    List<EstoqueUsuarioMedicamentoEntity> findByUsuarioId(@Param("usuarioId") int usuarioId);

    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.usuarioId = :usuarioId AND e.medicamentoId = :medicamentoId")
    Optional<EstoqueUsuarioMedicamentoEntity> findByUsuarioIdAndMedicamentoId(@Param("usuarioId") int usuarioId, @Param("medicamentoId") int medicamentoId);

    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.medicamentoId = :medicamentoId")
    List<EstoqueUsuarioMedicamentoEntity> findByMedicamentoId(@Param("medicamentoId") int medicamentoId);
}
