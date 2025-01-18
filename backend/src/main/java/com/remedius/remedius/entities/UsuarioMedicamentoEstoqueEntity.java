package com.remedius.remedius.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicamento_estoques")
public class UsuarioMedicamentoEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_medicacoes_id", nullable = false)
    private Integer usuarioMedicacoesId;

    private Integer quantidade;

    @Column(name = "ultima_compra")
    private LocalDateTime ultimaCompra;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioMedicacoesId() {
        return usuarioMedicacoesId;
    }

    public void setUsuarioMedicacoesId(Integer usuarioMedicacoesId) {
        this.usuarioMedicacoesId = usuarioMedicacoesId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDateTime ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
