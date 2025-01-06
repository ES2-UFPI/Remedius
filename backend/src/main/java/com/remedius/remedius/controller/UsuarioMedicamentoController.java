package com.remedius.remedius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.remedius.remedius.DTOs.MedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.service.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios-medicamentos")
public class UsuarioMedicamentoController {

    @Autowired
    private UsuarioMedicamentoService usuarioMedicamentoService;

    /**
     * Busca todas as medicações de um usuário.
     * 
     * @param usuarioId ID do usuário.
     * @return Lista de relações entre o usuário e suas medicações.
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<UsuarioMedicamentoEntity>> getUserMedications(@PathVariable Integer usuarioId) {
        List<UsuarioMedicamentoEntity> medicacoes = usuarioMedicamentoService.buscarMedicamentosDoUsuario(usuarioId);
        return ResponseEntity.ok(medicacoes);
    }

    /**
     * Adiciona uma medicação a um usuário.
     * 
     * @param usuarioId   ID do usuário.
     * @param medicamento Entidade representando o medicamento.
     * @param dataInicial Data inicial do tratamento.
     * @param frequencia  Frequência da medicação.
     * @return A relação criada entre o usuário e a medicação.
     */

    @PostMapping("/{usuarioId}")
    public UsuarioMedicamentoEntity addMedicationToUser(
            @PathVariable Long usuarioId,
            @RequestBody MedicamentoRequest medicamentoRequest) {

        UsuarioMedicamentoEntity relacao = usuarioMedicamentoService.adicionarMedicamentoAoUsuario( usuarioId,medicamentoRequest);
        return relacao;
    }

    @PutMapping("/{usuarioMedicacaoId}")
    public UsuarioMedicamentoEntity updateMedication(
            @PathVariable Integer usuarioMedicacaoId,
            @RequestBody MedicamentoRequest medicamentoRequest) {
        UsuarioMedicamentoEntity relacao = usuarioMedicamentoService.atualizarMedicamentoDoUsuario(usuarioMedicacaoId, medicamentoRequest);
        return relacao;
    }

    /**
     * Remove uma medicação específica de um usuário.
     * 
     * @param usuarioMedicacaoId ID da relação entre o usuário e o medicamento.
     * @return Sem conteúdo ao remover com sucesso.
     */
    @DeleteMapping("/{usuarioMedicacaoId}")
    public ResponseEntity<Void> removeMedicationToUser(@PathVariable Integer usuarioMedicacaoId) {
        usuarioMedicamentoService.removerMedicamentoDoUsuario(usuarioMedicacaoId);
        return ResponseEntity.noContent().build();
    }
}
