package com.remedius.remedius.repository;

import com.remedius.remedius.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioMedicamentoEstoqueRepository extends JpaRepository<UsuarioMedicamentoEstoqueEntity, Long> {

    @Query("""

            SELECT me

            FROM UsuarioMedicamentoEstoqueEntity me

            JOIN FETCH me.usuarioMedicamento um

            JOIN FETCH um.medicamento

            WHERE me.id = :id

            """)

    Optional<UsuarioMedicamentoEstoqueEntity> findByIdWithRelationships(Long id);

}
