
package com.remedius.remedius.controller;
import com.remedius.remedius.DTOs.MedicamentoRequest;
import com.remedius.remedius.entities.UsuarioMedicamentoEntity;
import com.remedius.remedius.service.UsuarioMedicamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;





public class UsuarioMedicamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioMedicamentoService usuarioMedicamentoService;

    @InjectMocks
    private UsuarioMedicamentoController usuarioMedicamentoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioMedicamentoController).build();
    }

    @Test
    public void testGetUserMedications() throws Exception {
        List<UsuarioMedicamentoEntity> medicacoes = Arrays.asList(new UsuarioMedicamentoEntity(), new UsuarioMedicamentoEntity());
        when(usuarioMedicamentoService.buscarMedicamentosDoUsuario(anyInt())).thenReturn(medicacoes);
        
        mockMvc.perform(get("/usuarios-medicamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(medicacoes.size()));
    }

    @Test
    public void testAddMedicationToUser() throws Exception {
        UsuarioMedicamentoEntity relacao = new UsuarioMedicamentoEntity();
        when(usuarioMedicamentoService.adicionarMedicamentoAoUsuario(anyInt(), any(MedicamentoRequest.class))).thenReturn(relacao);

        mockMvc.perform(post("/usuarios-medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"medicamentoId\":1,\"dataInicial\":\"2023-01-01\",\"frequencia\":\"diaria\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(relacao.getId()));
    }

    @Test
    public void testUpdateMedication() throws Exception {
        UsuarioMedicamentoEntity relacao = new UsuarioMedicamentoEntity();
        when(usuarioMedicamentoService.atualizarMedicamentoDoUsuario(anyInt(), any(MedicamentoRequest.class))).thenReturn(relacao);

        mockMvc.perform(put("/usuarios-medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"medicamentoId\":1,\"dataInicial\":\"2023-01-01\",\"frequencia\":\"diaria\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(relacao.getId()));
    }

    @Test
    public void testRemoveMedicationToUser() throws Exception {
        mockMvc.perform(delete("/usuarios-medicamentos/1"))
                .andExpect(status().isNoContent());
    }
}