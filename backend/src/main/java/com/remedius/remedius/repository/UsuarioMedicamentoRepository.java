package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository
@Repository
public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamentoEntity, Long> {
    Optional<UsuarioMedicamentoEntity> findByUsuarioIdAndMedicamentoId(Long usuarioId, Long medicamentoId);
    boolean existsByUsuarioIdAndMedicamentoId(Long usuarioId, Long medicamentoId);
}
