package com.remedius.remedius.service;

import com.remedius.remedius.DTOs.MedicamentoRequest;
import com.remedius.remedius.entities.*;
import com.remedius.remedius.repository.UsuarioMedicamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioMedicamentoServiceTest {

    @InjectMocks
    private UsuarioMedicamentoService usuarioMedicamentoService;

    @Mock
    private UsuarioMedicamentoRepository usuarioMedicamentoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private MedicamentoService medicamentoService;

    private UsuarioEntity usuario;
    private MedicamentoEntity medicamento;
    private UsuarioMedicamentoEntity usuarioMedicamento;
    private MedicamentoRequest medicamentoRequest;

    @BeforeEach
    public void setUp() {
        usuario = new UsuarioEntity();
        usuario.setId(1L);

        medicamento = new MedicamentoEntity();
        medicamento.setId(1L);

        usuarioMedicamento = new UsuarioMedicamentoEntity();
        usuarioMedicamento.setId(1L);
        usuarioMedicamento.setUsuario(usuario);
        usuarioMedicamento.setMedicamento(medicamento);

        medicamentoRequest = new MedicamentoRequest();
        medicamentoRequest.setMedicamentoId(1L);
        medicamentoRequest.setDataInicial("2023-01-01");
        medicamentoRequest.setFrequencia("daily");
        medicamentoRequest.setDosagem(1.0);
        medicamentoRequest.setQuantidadeInicialEstoque(10);
    }

    @Test
    public void testAdicionarMedicamentoAoUsuario() {
        when(usuarioService.getUsuarioById(1L)).thenReturn(usuario);
        when(medicamentoService.getMedicamentoById(1L)).thenReturn(medicamento);
        when(usuarioMedicamentoRepository.save(any(UsuarioMedicamentoEntity.class))).thenReturn(usuarioMedicamento);

        UsuarioMedicamentoEntity result = usuarioMedicamentoService.adicionarMedicamentoAoUsuario(1L,
                medicamentoRequest);

        // printar o result
        System.out.println(result);

        assertNotNull(result);
        assertEquals(usuario, result.getUsuario());
        assertEquals(medicamento, result.getMedicamento());
        verify(usuarioMedicamentoRepository, times(1)).save(any(UsuarioMedicamentoEntity.class));
    }

    @Test
    public void testRemoverMedicamentoDoUsuario() {
        doNothing().when(usuarioMedicamentoRepository).deleteById(1);

        usuarioMedicamentoService.removerMedicamentoDoUsuario(1);

        verify(usuarioMedicamentoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testBuscarMedicamentosDoUsuario() {
        List<UsuarioMedicamentoEntity> lista = new ArrayList<>();
        lista.add(usuarioMedicamento);

        when(usuarioMedicamentoRepository.findByUsuarioId(1)).thenReturn(lista);

        List<UsuarioMedicamentoEntity> result = usuarioMedicamentoService.buscarMedicamentosDoUsuario(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuarioMedicamento, result.get(0));
        verify(usuarioMedicamentoRepository, times(1)).findByUsuarioId(1);
    }

    @Test
    public void testAtualizarMedicamentoDoUsuario() {
        when(usuarioMedicamentoRepository.findById(1)).thenReturn(Optional.of(usuarioMedicamento));
        when(usuarioMedicamentoRepository.save(any(UsuarioMedicamentoEntity.class))).thenReturn(usuarioMedicamento);

        UsuarioMedicamentoEntity result = usuarioMedicamentoService.atualizarMedicamentoDoUsuario(1,
                medicamentoRequest);

        assertNotNull(result);
        assertEquals("2023-01-01", result.getDataInicial());
        assertEquals("daily", result.getFrequencia());
        assertEquals(1.0, result.getDosagem());
        assertEquals(10, result.getQuantidadeInicialEstoque());
        verify(usuarioMedicamentoRepository, times(1)).save(any(UsuarioMedicamentoEntity.class));
    }
}