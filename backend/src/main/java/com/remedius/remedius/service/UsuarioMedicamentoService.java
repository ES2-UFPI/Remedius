package com.remedius.remedius.service;

import com.remedius.remedius.DTOs.MedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//import java.time.LocalDateTime;

@Service
public class UsuarioMedicamentoService {

    @Autowired
    private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MedicamentoService medicamentoService;

    public UsuarioMedicamentoEntity adicionarMedicamentoAoUsuario(Integer usuarioId,
            MedicamentoRequest medicamentoRequest) {

        Long medicamentoId = medicamentoRequest.getMedicamentoId();
        String dataInicial = medicamentoRequest.getDataInicial();
        String frequencia = medicamentoRequest.getFrequencia();
        Double dosagem = medicamentoRequest.getDosagem();
        
        UsuarioEntity usuario = usuarioService.getUsuarioById(usuarioId);
        MedicamentoEntity medicamento = medicamentoService.getMedicamentoById(medicamentoId);
        UsuarioMedicamentoEntity relacao = new UsuarioMedicamentoEntity();

        relacao.setUsuario(usuario);
        relacao.setMedicamento(medicamento);
        relacao.setDataInicial(dataInicial);
        relacao.setFrequencia(frequencia);
        relacao.setDosagem(dosagem);

        usuario.getUsuarioMedicacacoes().add(relacao);
        return usuarioMedicamentoRepository.save(relacao);
    }

    public void removerMedicamentoDoUsuario(Integer usuarioMedicamentoId) {
        usuarioMedicamentoRepository.deleteById(usuarioMedicamentoId);
    }

    public List<UsuarioMedicamentoEntity> buscarMedicamentosDoUsuario(Integer usuarioId) {
        return usuarioMedicamentoRepository.findByUsuarioId(usuarioId);
    }

    public UsuarioMedicamentoEntity atualizarMedicamentoDoUsuario(Integer usuarioMedicamentoId,
            MedicamentoRequest medicamentoRequest) {
        UsuarioMedicamentoEntity relacao = usuarioMedicamentoRepository.findById(usuarioMedicamentoId).get();
        Long medicamentoId = medicamentoRequest.getMedicamentoId();
        String dataInicial = medicamentoRequest.getDataInicial();
        String frequencia = medicamentoRequest.getFrequencia();
        Double dosagem = medicamentoRequest.getDosagem();
        MedicamentoEntity medicamento = medicamentoService.getMedicamentoById(medicamentoId);

        relacao.setMedicamento(medicamento);
        relacao.setDataInicial(dataInicial);
        relacao.setFrequencia(frequencia);
        relacao.setDosagem(dosagem);

        return usuarioMedicamentoRepository.save(relacao);
    }
}
