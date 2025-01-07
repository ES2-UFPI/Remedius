package com.remedius.remedius.service;

import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import com.remedius.remedius.repository.EstoqueUsuarioMedicamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstoqueUsuarioMedicamentoServiceTest {

    @InjectMocks
    private EstoqueUsuarioMedicamentoService estoqueService;

    @Mock
    private EstoqueUsuarioMedicamentoRepository estoqueRepository;

    private EstoqueUsuarioMedicamentoEntity estoque;

    @BeforeEach
    public void setUp() {
        estoque = new EstoqueUsuarioMedicamentoEntity();
        estoque.setId(1L);
        estoque.setUltimaCompra(LocalDateTime.now());
        estoque.setStatus("Ativo");
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

        EstoqueUsuarioMedicamentoEntity result = estoqueService.createEstoque(estoque);

        assertNotNull(result);
        assertEquals(estoque, result);
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test
    public void testUpdateEstoque() {
        EstoqueUsuarioMedicamentoEntity updatedEstoque = new EstoqueUsuarioMedicamentoEntity();
        updatedEstoque.setQuantidade(20);
        estoque.setUltimaCompra(LocalDateTime.now());
        updatedEstoque.setStatus("Inativo");

        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        when(estoqueRepository.save(any(EstoqueUsuarioMedicamentoEntity.class))).thenReturn(updatedEstoque);

        Optional<EstoqueUsuarioMedicamentoEntity> result = estoqueService.updateEstoque(1L, updatedEstoque);

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

        boolean result = estoqueService.deleteEstoque(1L);

        assertTrue(result);
        verify(estoqueRepository, times(1)).existsById(1L);
        verify(estoqueRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteEstoqueNotFound() {
        when(estoqueRepository.existsById(1L)).thenReturn(false);

        boolean result = estoqueService.deleteEstoque(1L);

        assertFalse(result);
        verify(estoqueRepository, times(1)).existsById(1L);
        verify(estoqueRepository, times(0)).deleteById(1L);
    }
}
