package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository
@Repository
public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamentoEntity, Long> {
    Optional<UsuarioMedicamentoEntity> findByUsuarioIdAndMedicamentoId(Long usuarioId, Long medicamentoId);
    boolean existsByUsuarioIdAndMedicamentoId(Long usuarioId, Long medicamentoId);

    @Query("SELECT u FROM UsuarioMedicamentoEntity u WHERE u.usuario.id = :usuarioId")
    List<UsuarioMedicamentoEntity> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
