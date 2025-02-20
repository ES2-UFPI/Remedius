package com.remedius.remedius.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.remedius.remedius.enums.StatusTratamentoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TratamentoEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne

    @JoinColumn(name = "usuario_medicamento_id")
    @JsonIgnore
    private UsuarioMedicamentoEntity usuarioMedicamento;

    private LocalDateTime dataInicial;

    private Integer frequencia;

    private Integer duracao;

    private Double dosagem;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private StatusTratamentoEnum status;

    private Boolean ativo;

    // TODO: Criar a relação com Evento
    @OneToMany(mappedBy = "tratamento")
    private List<TratamentoEventoEntity> eventos;
}
