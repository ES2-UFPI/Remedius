package com.remedius.remedius.service;

import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioMedicamentoService {

    @Autowired
    private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioMedicamentoEntity adicionarMedicamentoAoUsuario(
            Integer usuarioId,
            MedicamentoEntity medicamento,
            LocalDateTime dataInicial,
            LocalDateTime frequencia) {

        UsuarioEntity usuario = usuarioService.getUsuarioById(usuarioId);

        UsuarioMedicamentoEntity relacao = new UsuarioMedicamentoEntity();

        relacao.setUsuario(usuario);
        relacao.setMedicamento(medicamento);
        relacao.setDataInicial(dataInicial);
        relacao.setFrequencia(frequencia);

        usuario.getUsuarioMedicacacoes().add(relacao);
        return usuarioMedicamentoRepository.save(relacao);
    }

    public void removerMedicamentoDoUsuario(Integer usuarioMedicamentoId) {
        usuarioMedicamentoRepository.deleteById(usuarioMedicamentoId);
    }
}
