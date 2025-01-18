package com.remedius.remedius.repository;

import com.remedius.remedius.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioMedicamentoEstoqueRepository extends JpaRepository<UsuarioMedicamentoEstoqueEntity, Integer> {
    
    Optional<UsuarioMedicamentoEstoqueEntity> findByUsuarioMedicacoesId(Integer usuarioMedicacoesId);
    Optional<UsuarioMedicamentoEstoqueEntity> findById(Integer id);
}
