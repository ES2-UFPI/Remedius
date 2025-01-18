package com.remedius.remedius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import java.util.List;

@RestController
@RequestMapping("/estoque")
public class UsuarioMedicamentoEstoqueController {

    private final UsuarioMedicamentoEstoqueService service;

    public UsuarioMedicamentoEstoqueController(UsuarioMedicamentoEstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioMedicamentoEstoqueEntity>> getAllEstoques() {
        return ResponseEntity.ok(service.getAllEstoques());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioMedicamentoEstoqueEntity> getEstoqueById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getEstoqueById(id));
    }

    @PutMapping("/acrescimo/{id}")
    public ResponseEntity<UsuarioMedicamentoEstoqueEntity> updateEstoqueAcrescimo(
            @PathVariable Integer id,
            @RequestBody UsuarioMedicamentoEstoqueEntity updatedEstoque) {
        return ResponseEntity.ok(service.updateEstoqueAcrescimo(id, updatedEstoque));
    }

    @PutMapping("/decrescimo/{id}")
    public ResponseEntity<UsuarioMedicamentoEstoqueEntity> updateEstoqueDecrescimo(
            @PathVariable Integer id,
            @RequestBody UsuarioMedicamentoEstoqueEntity updatedEstoque) {
        return ResponseEntity.ok(service.updateEstoqueDecrescimo(id, updatedEstoque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Integer id) {
        service.deleteEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
