package com.remedius.remedius.entities;

import java.time.LocalDateTime;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.remedius.remedius.service.EstoqueUsuarioMedicamentoService;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
@Table(name = "estoque_usuario_medicacoes")
public class EstoqueUsuarioMedicamentoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "medicacao_id", nullable = false)
    private MedicamentoEntity medicamento;

    @Column(name = "quantidade", nullable = false)
    @NonNull
    private Integer quantidade;

    @Column(name = "ultima_compra", nullable = false)
    private LocalDateTime ultimaCompra;

    @Column(name = "status", nullable = false)
    @NonNull
    private String status;

    @Column(name = "duracao_estimada", nullable = false)
    @NonNull
    private Integer duracaoEstimada;

    public EstoqueUsuarioMedicamentoEntity() {
    }

    public EstoqueUsuarioMedicamentoEntity(UsuarioEntity usuario, MedicamentoEntity medicamento, Integer quantidade, LocalDateTime ultimaCompra, String status) {
        this.usuario = usuario;
        this.medicamento = medicamento;
        this.quantidade = quantidade;
        this.ultimaCompra = ultimaCompra;
        this.status = status;
        this.duracaoEstimada = 0;
    }

    public Long getId() {
        return id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public MedicamentoEntity getMedicamento() {
        return medicamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getUltimaCompra() {
        return ultimaCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setMedicamento(MedicamentoEntity medicamento) {
        this.medicamento = medicamento;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setUltimaCompra(LocalDateTime ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(Integer duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    @Override
    public String toString() {
        return "EstoqueUsuarioMedicacoesEntity{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", medicamento=" + medicamento +
                ", quantidade=" + quantidade +
                ", ultimaCompra=" + ultimaCompra +
                ", status='" + status + '\'' +
                ", duração_estimada=" + duracaoEstimada +
                '}';
    }
}
