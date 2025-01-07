package com.remedius.remedius.service;

import com.remedius.remedius.entities.EstoqueUsuarioMedicamentoEntity;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.repository.EstoqueUsuarioMedicamentoRepository;
import com.remedius.remedius.repository.UsuarioMedicamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstoqueUsuarioMedicamentoServiceTest {

    @InjectMocks
    private EstoqueUsuarioMedicamentoService estoqueService;

    @Mock
    private EstoqueUsuarioMedicamentoRepository estoqueRepository;

    @Mock
    private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    private EstoqueUsuarioMedicamentoEntity estoque;
    private UsuarioMedicamentoEntity usuarioMedicamento;

    @BeforeEach
    public void setUp() {
        estoque = new EstoqueUsuarioMedicamentoEntity();
        estoque.setId(1L);
        estoque.setQuantidade(100);
        estoque.setStatus("Ativo");

        usuarioMedicamento = new UsuarioMedicamentoEntity();
        usuarioMedicamento.setId(1L);
        usuarioMedicamento.setDosagem(2.0);
        usuarioMedicamento.setFrequencia("12h");
    }

    @Test
    public void testCalcularDuracaoEstoque() {
        // Configurar mocks
        when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);
        when(usuarioMedicamentoRepository.findByUsuarioIdAndMedicamentoId(1L, 1L)).thenReturn(usuarioMedicamento);

        // Executar o método
        int duracao = estoqueService.calcularDuracaoEstoque(1L, 1L);

        // Validar resultado
        assertEquals(25, duracao, "A duração do estoque está incorreta.");
        verify(estoqueRepository, times(1)).findByUserMedicationId(1L, 1L);
        verify(usuarioMedicamentoRepository, times(1)).findByUsuarioIdAndMedicamentoId(1L, 1L);
    }

    @Test
    public void testCalcularDuracaoEstoqueEstoqueNaoEncontrado() {
        when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            estoqueService.calcularDuracaoEstoque(1L, 1L)
        );

        assertEquals("Estoque não encontrado para o usuário e medicamento fornecidos.", exception.getMessage());
        verify(estoqueRepository, times(1)).findByUserMedicationId(1L, 1L);
    }

    @Test
    public void testCalcularDuracaoEstoqueUsuarioMedicamentoNaoEncontrado() {
        when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);
        when(usuarioMedicamentoRepository.findByUsuarioIdAndMedicamentoId(1L, 1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            estoqueService.calcularDuracaoEstoque(1L, 1L)
        );

        assertEquals("Relação Usuário-Medicamento não encontrada.", exception.getMessage());
        verify(estoqueRepository, times(1)).findByUserMedicationId(1L, 1L);
        verify(usuarioMedicamentoRepository, times(1)).findByUsuarioIdAndMedicamentoId(1L, 1L);
    }

    @Test
    public void testCalcularDosesPorDiaComFrequenciaEmHoras() {
        // Método privado que podemos testar indiretamente via a lógica principal
        usuarioMedicamento.setFrequencia("8h");
        when(estoqueRepository.findByUserMedicationId(1L, 1L)).thenReturn(estoque);
        when(usuarioMedicamentoRepository.findByUsuarioIdAndMedicamentoId(1L, 1L)).thenReturn(usuarioMedicamento);

        int duracao = estoqueService.calcularDuracaoEstoque(1L, 1L);

        assertEquals(50, duracao, "A duração do estoque está incorreta para frequência de 8 horas.");
    }
}