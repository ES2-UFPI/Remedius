package com.remedius.remedius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    // quero gerar requisições para o backend
    // requisições: GET, POST, PUT, DELETE

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Integer id) {
        UsuarioEntity usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> createUsuario(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity createdUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> updateUsuarioById(@PathVariable Integer id, @RequestBody UsuarioEntity usuario) {
        UsuarioEntity updatedUsuario = usuarioService.updateUsuarioById(id, usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Integer id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.noContent().build();
    }  
}
