package com.remedius.remedius.repository;

import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueUsuarioMedicamentoRepository extends JpaRepository<EstoqueUsuarioMedicamentoEntity, Long> {

    // Buscar Estoques pelo ID do usuário
    List<EstoqueUsuarioMedicamentoEntity> findByUsuarioId(Long usuarioId);

    // Consulta customizada para buscar estoques por status
    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.status = :status")
    List<EstoqueUsuarioMedicamentoEntity> findByStatus(@Param("status") String status);

    // Consulta customizada para buscar estoques por quantidade mínima
    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.quantidade >= :minQuantidade")
    List<EstoqueUsuarioMedicamentoEntity> findByMinQuantidade(@Param("minQuantidade") int minQuantidade);

    // Buscar Estoque pelo ID
    Optional<EstoqueUsuarioMedicamentoEntity> findById(Long id);
}
