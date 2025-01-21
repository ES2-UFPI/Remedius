package com.remedius.remedius.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.remedius.remedius.DTOs.AtualizarEstoqueDTO;
import com.remedius.remedius.DTOs.CriarEstoqueDTO;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.entities.UsuarioMedicamentoEstoqueEntity;
import com.remedius.remedius.enums.StatusEstoque;
import com.remedius.remedius.repository.UsuarioMedicamentoEstoqueRepository;
import com.remedius.remedius.repository.UsuarioMedicamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioMedicamentoEstoqueService {

    private final UsuarioMedicamentoEstoqueRepository medicamentoEstoqueRepository;

    private final UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    public UsuarioMedicamentoEstoqueService(
            UsuarioMedicamentoEstoqueRepository medicamentoEstoqueRepository,
            UsuarioMedicamentoRepository usuarioMedicamentoRepository) {
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
        this.usuarioMedicamentoRepository = usuarioMedicamentoRepository;
    }

    @Transactional
    public UsuarioMedicamentoEstoqueEntity criarEstoque(CriarEstoqueDTO estoque) throws NotFoundException {

        UsuarioMedicamentoEntity usuarioMedicamento = usuarioMedicamentoRepository
                .findById(estoque.getUsuarioMedicamentoId())
                .orElseThrow(() -> new NotFoundException());

        UsuarioMedicamentoEstoqueEntity estoqueCriado = new UsuarioMedicamentoEstoqueEntity();

        estoqueCriado.setUsuarioMedicamento(usuarioMedicamento);

        estoqueCriado.setQuantidade(estoque.getQuantidade());

        estoqueCriado.setStatus(estoque.getStatus());

        estoqueCriado.setUltimaCompra(estoque.getUltimaCompra());

        return medicamentoEstoqueRepository.save(estoqueCriado);

    }

    @Transactional
    public UsuarioMedicamentoEstoqueEntity atualizarEstoque(AtualizarEstoqueDTO dto) throws Exception {

        UsuarioMedicamentoEstoqueEntity estoque = medicamentoEstoqueRepository.findByIdWithRelationships(dto.getId())

                .orElseThrow(() -> new NotFoundException());

        if (dto.getQuantidade() != null) {

            // Se quantidade for zero, atualiza status para FINALIZADO

            if (dto.getQuantidade() == 0) {

                estoque.setStatus(StatusEstoque.FINALIZADO);

            }

            estoque.setQuantidade(dto.getQuantidade());

        }

        if (dto.getStatus() != null) {

            // Se status for FINALIZADO, zera a quantidade

            if (dto.getStatus() == StatusEstoque.FINALIZADO) {

                estoque.setQuantidade(0);

            }

            estoque.setStatus(dto.getStatus());

        }

        if (dto.getUltimaCompra() != null) {

            if (dto.getUltimaCompra().isAfter(LocalDateTime.now())) {

                throw new Exception("Data de última compra não pode ser futura");

            }

            estoque.setUltimaCompra(dto.getUltimaCompra());

        }

        return medicamentoEstoqueRepository.save(estoque);

    }

    public List<UsuarioMedicamentoEstoqueEntity> listarEstoques() {
        return medicamentoEstoqueRepository.findAll();
    }

    public List<UsuarioMedicamentoEstoqueEntity> listarEstoquePorUsuarioMedicamentoId(Long id) throws NotFoundException {
        return medicamentoEstoqueRepository.findByUsuarioMedicamentoId(id);
    }
}
