package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioMedicamentoRepository extends JpaRepository<UsuarioMedicamentoEntity, Integer> {

    // Busca todas as medicações de um usuário específico pelo ID do usuário
    List<UsuarioMedicamentoEntity> findByUsuarioId(Integer usuarioId);

    // Busca todas as instâncias de relacionamento para um medicamento específico pelo ID do medicamento
    List<UsuarioMedicamentoEntity> findByMedicamentoId(Integer medicamentoId);
}
