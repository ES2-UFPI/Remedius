/*
 * A classe Controladora se chama controladora por CONTROLAR OS ACESSOS à nossa API
 * A partir dela expomos os endpoints pelas anotações GET, PUT, ..., DELETE que receberão
 * requisições web e invocarão os métodos SERVICE 
 */
package com.remedius.remedius.controller;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import java.util.List;


@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    // quero gerar requisições para o backend
    // requisições: GET, POST, PUT, DELETE
    // gere o codigo para cada uma dessas requisições
    
    private MedicamentoService medicamentoService;

    @Autowired
    /*
     * @AutoWired:
     * o Spring irá localizar, instanciar e "injetar" um objeto necessário em um campo,
     * construtor ou método de uma classe, sem que você precise criar explicitamente uma
     * nova instância desse objeto.
     */
    public MedicamentoController(MedicamentoService medicamentoService){
        this.medicamentoService = medicamentoService;
    }

    
    /*Expondo o método ao verbo HTTP POST pela rota /create
     *Determinando que a entidade deve ser passada pelo Body da requisição HTTP 
    */ 
    @PostMapping
    List<MedicamentoEntity> createMedicamento(@RequestBody MedicamentoEntity medicamentoEntity){
        return medicamentoService.createMedicamento(medicamentoEntity);
    }


    //Expondo o método ao verbo HTTP GET pela rota /create
    @GetMapping
    public List<MedicamentoEntity> getAllMedicamentos(MedicamentoEntity medicamentoEntity) {
        return medicamentoService.getAllMedicamentos();
    }


    /*Expondo o método ao verbo HTTP PUT pela rota /create
     *Determinando que a entidade deve ser passada pelo Body da requisição HTTP 
    */
    @PutMapping
    public List<MedicamentoEntity> updateMedicamento(@RequestAttribute MedicamentoEntity medicamentoEntity) {
        return medicamentoService.updateMedicamento(medicamentoEntity);
    }


    /*Expondo o método ao verbo HTTP DELETE
     *Determinando que o ID deve ser passado pela Rota da requisição HTTP
     *Nesse caso o ID é uma PathVariable e deve ser recuperada da Requisição 
     * DELETE medicamentos/1 -> esse 1 é o ID
     */
    @DeleteMapping("{id}")
    public List<MedicamentoEntity> deleteMedicamento(@PathVariable("id") Long id) {
        return medicamentoService.deleteMedicamento(id);
    }

    /*@GetMapping
    public ResponseEntity<List<MedicamentoEntity>> getAllMedicamentos() {
        List<MedicamentoEntity> medicamentos = medicamentoService.getAllMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<MedicamentoEntity> getMedicamentoByName(@PathVariable String name) {
        MedicamentoEntity medicamento = medicamentoService.getMedicamentoByName(name);
        return ResponseEntity.ok(medicamento);
    }

    @PostMapping
    public ResponseEntity<MedicamentoEntity> createMedicamento(@RequestBody MedicamentoEntity medicamento) {
        MedicamentoEntity createdMedicamento = medicamentoService.createMedicamento(medicamento);
        return ResponseEntity.ok(createdMedicamento);
    }

    @PutMapping("/{name}")
    public ResponseEntity<MedicamentoEntity> updateMedicamentoByName(@PathVariable String name, @RequestBody MedicamentoEntity medicamento) {
        MedicamentoEntity updatedMedicamento = medicamentoService.updateMedicamentoByName(name, medicamento);
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMedicamentoByName(@PathVariable String name) {
        medicamentoService.deleteMedicamentoByName(name);
        return ResponseEntity.noContent().build();
    }

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
    }*/
}

