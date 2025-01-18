package com.remedius.remedius.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.*;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.*;
// import com.remedius.remedius.service.*;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioMedicamentoEstoqueService {

    private final UsuarioMedicamentoEstoqueRepository repository;

    public UsuarioMedicamentoEstoqueService(UsuarioMedicamentoEstoqueRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioMedicamentoEstoqueEntity> getAllEstoques() {
        return repository.findAll();
    }



    public UsuarioMedicamentoEstoqueEntity getEstoqueById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Optional<UsuarioMedicamentoEstoqueEntity> getEstoqueByUsuarioMedicacoesId(Integer usuarioMedicacoesId) {
        return repository.findByUsuarioMedicacoesId(usuarioMedicacoesId);
    }

    public UsuarioMedicamentoEstoqueEntity createEstoque(UsuarioMedicamentoEstoqueEntity estoque) {
        return repository.save(estoque);
    }

    public UsuarioMedicamentoEstoqueEntity updateEstoqueAcrescimo(Integer id, UsuarioMedicamentoEstoqueEntity updatedEstoque) {
        UsuarioMedicamentoEstoqueEntity estoque = getEstoqueById(id);
        if (estoque == null) {
            return createEstoque(estoque);
        }
        estoque.setQuantidade(estoque.getQuantidade() + updatedEstoque.getQuantidade());
        estoque.setUltimaCompra(updatedEstoque.getUltimaCompra());
        estoque.setStatus(updatedEstoque.getStatus());
        return repository.save(estoque);
    }

    public UsuarioMedicamentoEstoqueEntity updateEstoqueDecrescimo(Integer id, UsuarioMedicamentoEstoqueEntity updatedEstoque) {
        UsuarioMedicamentoEstoqueEntity estoque = getEstoqueById(id);
        if (estoque == null) {
            return createEstoque(estoque);
        }
        Integer diferenca_de_estoque = estoque.getQuantidade() - updatedEstoque.getQuantidade();
        if (diferenca_de_estoque < 0) {
            return null;
        }else{
            estoque.setQuantidade(diferenca_de_estoque);
            estoque.setUltimaCompra(estoque.getUltimaCompra());
            estoque.setStatus(updatedEstoque.getStatus());
            return repository.save(estoque);
        }
    }

    public void deleteEstoque(Integer id) {
        UsuarioMedicamentoEstoqueEntity estoque = getEstoqueById(id);
        repository.delete(estoque);
    }
}
