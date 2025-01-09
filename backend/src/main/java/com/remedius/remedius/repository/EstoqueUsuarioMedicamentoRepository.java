package com.remedius.remedius.repository;

import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueUsuarioMedicamentoRepository extends JpaRepository<EstoqueUsuarioMedicamentoEntity, Long> {

    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.usuario.id = :usuarioId AND e.medicamento.id = :medicamentoId")
    EstoqueUsuarioMedicamentoEntity findByUserMedicationId(@Param("usuarioId") Long usuarioId, @Param("medicamentoId") Long medicamentoId);

    // Buscar Estoques pelo ID do usuário
    @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.usuario.id = :usuarioId")
    List<EstoqueUsuarioMedicamentoEntity> findByUserId(@Param("usuarioId") Long usuarioId);

    
    // // Consulta customizada para buscar estoques por status
    // @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.status = :status")
    // List<EstoqueUsuarioMedicamentoEntity> findByStatus(@Param("status") String status);

    // // Consulta customizada para buscar estoques por quantidade mínima
    // @Query("SELECT e FROM EstoqueUsuarioMedicamentoEntity e WHERE e.quantidade >= :minQuantidade")
    // List<EstoqueUsuarioMedicamentoEntity> findByMinQuantidade(@Param("minQuantidade") int minQuantidade);

}