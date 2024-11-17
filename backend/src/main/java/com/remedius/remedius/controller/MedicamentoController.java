package com.remedius.remedius.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    
    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<MedicamentoEntity>> getAllMedicamentos() {
        List<MedicamentoEntity> medicamentos = medicamentoService.getAllMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    
    @PostMapping
    public ResponseEntity<MedicamentoEntity> createMedicamento(@RequestBody MedicamentoEntity medicamento) {
        MedicamentoEntity createdMedicamento = medicamentoService.createMedicamento(medicamento);
        return ResponseEntity.ok(createdMedicamento);
    }


    /*
    @GetMapping("/{name}")
    public ResponseEntity<MedicamentoEntity> getMedicamentoByName(@PathVariable String name) {
        MedicamentoEntity medicamento = medicamentoService.getMedicamentoByName(name);
        return ResponseEntity.ok(medicamento);
    }

    
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMedicamentoByName(@PathVariable String name) {
        medicamentoService.deleteMedicamentoByName(name);
        return ResponseEntity.noContent().build();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoEntity> getMedicamentoById(@PathVariable Long id) {
        MedicamentoEntity medicamento = medicamentoService.getMedicamentoById(id);
        return ResponseEntity.ok(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoEntity> updateMedicamentoById(@PathVariable Long id, @RequestBody MedicamentoEntity medicamento) {
        MedicamentoEntity updatedMedicamento = medicamentoService.updateMedicamentoById(id, medicamento);
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamentoById(@PathVariable Long id) {
        medicamentoService.deleteMedicamentoById(id);
        return ResponseEntity.noContent().build();
    }
}

