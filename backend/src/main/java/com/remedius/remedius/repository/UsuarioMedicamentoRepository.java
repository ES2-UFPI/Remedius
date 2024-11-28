package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamentoEntity, Integer> {

    // Busca todas as medicações de um usuário específico pelo ID do usuário
    @Query("SELECT um FROM UsuarioMedicamentoEntity um WHERE um.usuario.id = :usuarioId")
    List<UsuarioMedicamentoEntity> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    // Busca todas as instâncias de relacionamento para um medicamento específico pelo ID do medicamento
    @Query("SELECT um FROM UsuarioMedicamentoEntity um WHERE um.medicamento.id = :medicamentoId")
    List<UsuarioMedicamentoEntity> findByMedicamentoId(@Param("medicamentoId") Integer medicamentoId);
}
