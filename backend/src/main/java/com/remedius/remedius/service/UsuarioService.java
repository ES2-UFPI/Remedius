package com.remedius.remedius.service;

import com.remedius.remedius.entities.UsuarioEntity;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET: Retorna todos os usuários
    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // POST: Cria um novo usuário
    public UsuarioEntity createUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    // GET: Retorna um usuário pelo ID
    public UsuarioEntity getUsuarioById(Integer id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    // PUT: Atualiza um usuário existente pelo ID
    public UsuarioEntity updateUsuarioById(Integer id, UsuarioEntity usuario) {
        
        Optional<UsuarioEntity> existingUsuario = usuarioRepository.findById(id);

        if (existingUsuario.isPresent()) {
            UsuarioEntity updatedUsuario = existingUsuario.get();
            updatedUsuario.setNome(usuario.getNome());
            updatedUsuario.setEmail(usuario.getEmail());
            updatedUsuario.setSenha(usuario.getSenha());
            
            return usuarioRepository.save(updatedUsuario);
        } else {
            return null;
        }
    }

    // DELETE: Deleta um usuário pelo ID
    public void deleteUsuarioById(Integer id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(usuarioRepository::delete);
    }

    // Método para buscar todas as medicações associadas a um usuário específico
    public Set<UsuarioMedicamentoEntity> buscarMedicacoesDoUsuario(Integer usuarioId) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(UsuarioEntity::getUsuarioMedicacacoes)
                      .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}
