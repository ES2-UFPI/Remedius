package com.remedius.remedius.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.remedius.remedius.DTOs.EstoqueUsuarioMedicamentoRequest;
import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import com.remedius.remedius.entities.MedicamentoEntity;
import com.remedius.remedius.entities.UsuarioEntity;
import com.remedius.remedius.repository.EstoqueUsuarioMedicamentoRepository;
import com.remedius.remedius.repository.MedicamentoRepository;
import com.remedius.remedius.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class EstoqueUsuarioMedicamentoServiceTest {

    @InjectMocks
    private EstoqueUsuarioMedicamentoService estoqueService;

    @Mock
    private EstoqueUsuarioMedicamentoRepository estoqueRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MedicamentoRepository medicamentoRepository;

    private UsuarioEntity usuario;
    private MedicamentoEntity medicamento;
    private EstoqueUsuarioMedicamentoEntity estoque;
    private EstoqueUsuarioMedicamentoRequest estoqueRequest;

    @BeforeEach
    public void setUp() {
        usuario = new UsuarioEntity();
        usuario.setId(1L);

        medicamento = new MedicamentoEntity();
        medicamento.setId(1L);

        estoque = new EstoqueUsuarioMedicamentoEntity();
        estoque.setId(1L);
        estoque.setUsuario(usuario);
        estoque.setMedicamento(medicamento);

        estoqueRequest = new EstoqueUsuarioMedicamentoRequest();
        estoqueRequest.setUsuarioId(1L);
        estoqueRequest.setMedicamentoId(1L);
        estoqueRequest.setQuantidade(10);
        estoqueRequest.setUltimaCompra(LocalDateTime.now());
        estoqueRequest.setStatus("Ativo");

    }

    @Test
    public void testGetAllEstoques() {
        List<EstoqueUsuarioMedicamentoEntity> estoques = List.of(estoque);
        when(estoqueRepository.findAll()).thenReturn(estoques);

        List<EstoqueUsuarioMedicamentoEntity> result = estoqueService.getAllEstoques();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(estoque, result.get(0));
        verify(estoqueRepository, times(1)).findAll();
    }

    @Test
    public void testGetEstoqueById() {
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

        Optional<EstoqueUsuarioMedicamentoEntity> result = estoqueService.getEstoqueById(1L);

        assertTrue(result.isPresent());
        assertEquals(estoque, result.get());
        verify(estoqueRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateEstoque() {
        when(estoqueRepository.save(estoque)).thenReturn(estoque);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(medicamento));
        when(estoqueRepository.save(any(EstoqueUsuarioMedicamentoEntity.class))).thenReturn(estoque);
        when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);

        EstoqueUsuarioMedicamentoEntity result = estoqueService.createEstoque(estoqueRequest);

        assertNotNull(result);
        assertEquals(estoque, result);
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test
    public void testUpdateEstoque() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(medicamento));

        EstoqueUsuarioMedicamentoRequest updatedEstoqueRequest = new EstoqueUsuarioMedicamentoRequest();
        updatedEstoqueRequest.setQuantidade(20);
        updatedEstoqueRequest.setUltimaCompra(LocalDateTime.now());
        updatedEstoqueRequest.setStatus("Inativo");

        EstoqueUsuarioMedicamentoEntity updatedEstoque = new EstoqueUsuarioMedicamentoEntity();
        updatedEstoque.setId(1L);
        updatedEstoque.setUsuario(usuario);
        updatedEstoque.setMedicamento(medicamento);
        updatedEstoque.setQuantidade(20);
        updatedEstoque.setUltimaCompra(LocalDateTime.now());
        updatedEstoque.setStatus("Inativo");

        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        when(estoqueRepository.save(any(EstoqueUsuarioMedicamentoEntity.class))).thenReturn(updatedEstoque);

        Optional<EstoqueUsuarioMedicamentoEntity> result = estoqueService
                .updateEstoqueByUserMedicationId(updatedEstoqueRequest);

        assertTrue(result.isPresent());
        assertEquals(20, result.get().getQuantidade());
        assertEquals("Inativo", result.get().getStatus());
        verify(estoqueRepository, times(1)).findById(1L);
        verify(estoqueRepository, times(1)).save(any(EstoqueUsuarioMedicamentoEntity.class));
    }

    @Test
    public void testDeleteEstoque() {
        when(estoqueRepository.existsById(1L)).thenReturn(true);
        doNothing().when(estoqueRepository).deleteById(1L);

        boolean result = estoqueService.deleteEstoqueById(1L);

        assertTrue(result);
        verify(estoqueRepository, times(1)).existsById(1L);
        verify(estoqueRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteEstoqueNotFound() {
        when(estoqueRepository.existsById(1L)).thenReturn(false);

        boolean result = estoqueService.deleteEstoqueById(1L);

        assertFalse(result);
        verify(estoqueRepository, times(1)).existsById(1L);
        verify(estoqueRepository, times(0)).deleteById(1L);
    }
}
