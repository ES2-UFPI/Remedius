
package com.remedius.remedius.controller;

import com.remedius.remedius.DTOs.EstoqueUsuarioMedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueUsuarioMedicamentoController {

    @Autowired
    private EstoqueUsuarioMedicamentoService EstoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<List<EstoqueUsuarioMedicamentoEntity>> getEstoqueByUserId(@PathVariable Long id) {
        List<EstoqueUsuarioMedicamentoEntity> estoques = EstoqueService.getEstoqueByUserId(id);
        return ResponseEntity.ok(estoques);
    }

    @PostMapping
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> createEstoque(
            @RequestBody EstoqueUsuarioMedicamentoRequest Estoque) {
        EstoqueUsuarioMedicamentoEntity newEstoque = EstoqueService.createEstoque(Estoque);
        return ResponseEntity.ok(newEstoque);
    }

    @PutMapping()
    public ResponseEntity<EstoqueUsuarioMedicamentoEntity> updateEstoqueByMedicationId(
            @RequestBody EstoqueUsuarioMedicamentoRequest updatedEstoque) {
        Optional<EstoqueUsuarioMedicamentoEntity> Estoque = EstoqueService
                .updateEstoqueByUserMedicationId(updatedEstoque);
        return Estoque.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
