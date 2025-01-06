
package com.remedius.remedius.controller;

import com.remedius.remedius.DTOs.EstoqueUsuarioMedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueUsuarioMedicamentoController {

    @Autowired
    private EstoqueUsuarioMedicamentoService EstoqueService;

    // @GetMapping
    // public ResponseEntity<List<EstoqueUsuarioMedicamentoEntity>> getAllEstoques() {
    //     List<EstoqueUsuarioMedicamentoEntity> Estoques = EstoqueService.getAllEstoques();
    //     return ResponseEntity.ok(Estoques);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<List<EstoqueUsuarioMedicamentoEntity>> getEstoqueByUserId(@PathVariable Long id) {
        List<EstoqueUsuarioMedicamentoEntity> estoques = EstoqueService.getEstoqueByUserId(id);
        return ResponseEntity.ok(estoques);
    }

    // @PostMapping
    // public ResponseEntity<EstoqueUsuarioMedicamentoEntity> createEstoque(@RequestBody EstoqueUsuarioMedicamentoEntity Estoque) {
    //     EstoqueUsuarioMedicamentoEntity newEstoque = EstoqueService.createEstoque(Estoque);
    //     return ResponseEntity.ok(newEstoque);
    // }

    @PutMapping()
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> updateEstoqueByMedicationId(
           @RequestBody EstoqueUsuarioMedicamentoRequest updatedEstoque) {
        EstoqueUsuarioMedicamentoEntity Estoque = EstoqueService.updateEstoqueByUserMedicationId(updatedEstoque);
        return ResponseEntity.ok(Estoque);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
    //     boolean deleted = EstoqueService.deleteEstoque(id);
    //     return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    // }
}
