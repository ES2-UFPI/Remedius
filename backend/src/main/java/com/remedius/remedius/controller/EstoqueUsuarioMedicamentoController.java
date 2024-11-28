package com.remedius.remedius.controller;

import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Estoque")
public class EstoqueUsuarioMedicamentoController {

    @Autowired
    private EstoqueUsuarioMedicamentoService EstoqueService;

    @GetMapping
    public ResponseEntity<List<EstoqueUsuarioMedicamentoEntity>> getAllEstoques() {
        List<EstoqueUsuarioMedicamentoEntity> Estoques = EstoqueService.getAllEstoques();
        return ResponseEntity.ok(Estoques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> getEstoqueById(@PathVariable Long id) {
        Optional<EstoqueUsuarioMedicamentoEntity> Estoque = EstoqueService.getEstoqueById(id);
        return Estoque.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> createEstoque(@RequestBody EstoqueUsuarioMedicamentoEntity Estoque) {
        EstoqueUsuarioMedicamentoEntity newEstoque = EstoqueService.createEstoque(Estoque);
        return ResponseEntity.ok(newEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> updateEstoque(
            @PathVariable Long id, @RequestBody EstoqueUsuarioMedicamentoEntity updatedEstoque) {
        Optional<EstoqueUsuarioMedicamentoEntity> Estoque = EstoqueService.updateEstoque(id, updatedEstoque);
        return Estoque.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
        boolean deleted = EstoqueService.deleteEstoque(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
