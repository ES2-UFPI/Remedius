package com.remedius.remedius.repository;

import com.remedius.remedius.entities.UsuarioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // Busca um usuário pelo e-mail
    @Query("SELECT u FROM UsuarioEntity u WHERE u.email = :email")
    Optional<UsuarioEntity> findByEmail(@Param("email") String email);
}