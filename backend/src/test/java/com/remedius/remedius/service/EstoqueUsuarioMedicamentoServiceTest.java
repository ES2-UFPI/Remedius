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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.remedius.remedius.DTOs.EstoqueUsuarioMedicamentoRequest;
import com.remedius.remedius.entities.MedicamentoEntity;
import com.remedius.remedius.entities.UsuarioEntity;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.repository.MedicamentoRepository;
import com.remedius.remedius.repository.UsuarioMedicamentoRepository;
import com.remedius.remedius.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class EstoqueUsuarioMedicamentoServiceTest {

    // @InjectMocks
    // private EstoqueUsuarioMedicamentoService estoqueService;

    // @Mock
    // private EstoqueUsuarioMedicamentoRepository estoqueRepository;

    // @Mock
    // private UsuarioRepository usuarioRepository;

    // @Mock
    // private MedicamentoRepository medicamentoRepository;

    // @Mock
    // private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    // @Captor
    // private ArgumentCaptor<EstoqueUsuarioMedicamentoEntity> estoqueCaptor;

    // @Captor
    // private ArgumentCaptor<Long> idCaptor;

    // private UsuarioEntity usuario;
    // private MedicamentoEntity medicamento;
    // private UsuarioMedicamentoEntity usuarioMedicamento;
    // private EstoqueUsuarioMedicamentoEntity estoque;
    // private EstoqueUsuarioMedicamentoRequest estoqueRequest;

    // @BeforeEach
    // public void setUp() {
    //     usuario = new UsuarioEntity();
    //     usuario.setId(1L);

    //     medicamento = new MedicamentoEntity();
    //     medicamento.setId(1L);

    //     // usuarioMedicamento = new UsuarioMedicamentoEntity();
    //     // usuarioMedicamento.setId(1L);
    //     // usuarioMedicamento.setUsuario(usuario);
    //     // usuarioMedicamento.setMedicamento(medicamento);
    //     // usuarioMedicamento.setDataInicial("2021-01-01");
    //     // usuarioMedicamento.setFrequencia("8h");
    //     // usuarioMedicamento.setDosagem(10.0);

    //     LocalDateTime hora = LocalDateTime.now();

    //     estoque = new EstoqueUsuarioMedicamentoEntity();
    //     estoque.setId(1L);
    //     estoque.setUsuario(usuario);
    //     estoque.setMedicamento(medicamento);
    //     estoque.setQuantidade(10);
    //     estoque.setUltimaCompra(hora);
    //     estoque.setStatus("Ativo");
    //     estoque.setDuracaoEstimada(1);

    //     estoqueRequest = new EstoqueUsuarioMedicamentoRequest();
    //     estoqueRequest.setUsuarioId(1L);
    //     estoqueRequest.setMedicamentoId(1L);
    //     estoqueRequest.setQuantidade(10);
    //     estoqueRequest.setUltimaCompra(hora);
    //     estoqueRequest.setStatus("Ativo");

    // }

    // @Test
    // public void testGetAllEstoques() {
    //     List<EstoqueUsuarioMedicamentoEntity> estoques = List.of(estoque);
    //     when(estoqueRepository.findAll()).thenReturn(estoques);

    //     List<EstoqueUsuarioMedicamentoEntity> result = estoqueService.getAllEstoques();

    //     assertNotNull(result);
    //     assertEquals(1, result.size());
    //     assertEquals(estoque, result.get(0));
    //     verify(estoqueRepository, times(1)).findAll();
    // }

    // @Test
    // public void testGetEstoqueById() {
    //     when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

    //     Optional<EstoqueUsuarioMedicamentoEntity> result = estoqueService.getEstoqueById(1L);

    //     assertTrue(result.isPresent());
    //     assertEquals(estoque, result.get());
    //     verify(estoqueRepository, times(1)).findById(1L);
    // }

    // @Test
    // public void testCreateEstoque() {
    //     when(estoqueRepository.save(any(EstoqueUsuarioMedicamentoEntity.class))).thenAnswer(invocation -> {
    //         EstoqueUsuarioMedicamentoEntity entity = invocation.getArgument(0);
    //         entity.setId(1L); // Simulate the database setting the ID
    //         return entity;
    //     });
    //     when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    //     when(medicamentoRepository.findById(1L)).thenReturn(Optional.of(medicamento));
    //     when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);
    //     // when(usuarioMedicamentoRepository.findByUsuarioIdAndMedicamentoId(1L, 1L)).thenReturn(usuarioMedicamento);

    //     EstoqueUsuarioMedicamentoEntity result = estoqueService.createEstoque(estoqueRequest);

    //     assertNotNull(result);

    //     verify(estoqueRepository, times(1)).save(estoqueCaptor.capture());
    //     EstoqueUsuarioMedicamentoEntity capturedEstoque = estoqueCaptor.getValue();
    //     assertNotNull(capturedEstoque.getId()); // Ensure the ID is set
    //     assertEquals(1L, capturedEstoque.getId()); // Check the ID value
    //     assertEquals(estoque.getUsuario(), capturedEstoque.getUsuario());
    //     assertEquals(estoque.getMedicamento(), capturedEstoque.getMedicamento());
    //     assertEquals(estoque.getQuantidade(), capturedEstoque.getQuantidade());
    //     assertEquals(estoque.getUltimaCompra(), capturedEstoque.getUltimaCompra());
    //     assertEquals(estoque.getStatus(), capturedEstoque.getStatus());
    //     assertEquals(estoque.getDuracaoEstimada(), capturedEstoque.getDuracaoEstimada());
    // }

    
    // @Test
    // public void testUpdateEstoque() {
        
    //     when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);

    //     EstoqueUsuarioMedicamentoRequest updatedEstoqueRequest = new EstoqueUsuarioMedicamentoRequest();
    //     updatedEstoqueRequest.setUsuarioId(1L); // Ensure the ID is set
    //     updatedEstoqueRequest.setMedicamentoId(1L); // Ensure the ID is set
    //     updatedEstoqueRequest.setQuantidade(20);
    //     updatedEstoqueRequest.setUltimaCompra(LocalDateTime.now());
    //     updatedEstoqueRequest.setStatus("Inativo");

    //     EstoqueUsuarioMedicamentoEntity updatedEstoque = new EstoqueUsuarioMedicamentoEntity();
    //     updatedEstoque.setId(1L);
    //     updatedEstoque.setUsuario(usuario);
    //     updatedEstoque.setMedicamento(medicamento);
    //     updatedEstoque.setQuantidade(20);
    //     updatedEstoque.setUltimaCompra(LocalDateTime.now());
    //     updatedEstoque.setStatus("Inativo");

        
    //     when(estoqueRepository.save(any(EstoqueUsuarioMedicamentoEntity.class))).thenReturn(updatedEstoque);

    //     Optional<EstoqueUsuarioMedicamentoEntity> result = estoqueService.updateEstoqueByUserMedicationId(updatedEstoqueRequest);

    //     assertTrue(result.isPresent());
    //     assertEquals(20, result.get().getQuantidade());
    //     assertEquals("Inativo", result.get().getStatus());

    //     verify(estoqueRepository, times(1)).save(any(EstoqueUsuarioMedicamentoEntity.class));

    // }

    // @Test
    // public void testDeleteEstoque() {
    //     when(estoqueRepository.existsById(1L)).thenReturn(true);
    //     doNothing().when(estoqueRepository).deleteById(1L);

    //     boolean result = estoqueService.deleteEstoqueById(1L);

    //     assertTrue(result);
    //     verify(estoqueRepository, times(1)).existsById(1L);
    //     verify(estoqueRepository, times(1)).deleteById(1L);
    // }

    // @Test
    // public void testDeleteEstoqueNotFound() {
    //     when(estoqueRepository.existsById(1L)).thenReturn(false);

    //     boolean result = estoqueService.deleteEstoqueById(1L);

    //     assertFalse(result);
    //     verify(estoqueRepository, times(1)).existsById(1L);
    //     verify(estoqueRepository, times(0)).deleteById(1L);
    // }
}
