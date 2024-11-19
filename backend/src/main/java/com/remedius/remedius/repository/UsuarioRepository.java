package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Busca um usuário pelo id (usado em getUsuarioById)
    Optional<UsuarioEntity> findById(Integer id);

    // Busca um usuário pelo e-mail
    Optional<UsuarioEntity> findByEmail(String email);

}
