package com.remedius.remedius.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO;
import com.remedius.remedius.entities.TratamentoEventoEntity;

@Repository
public interface TratamentoEventoRepository extends JpaRepository<TratamentoEventoEntity, Long> {
    @Query("""
        SELECT new com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO(
            te.id,
            m.nome,
            m.laboratorio,
            te.horario,
            t.dosagem,
            um.cor,
            te.status
        )
        FROM TratamentoEventoEntity te
        JOIN te.tratamento t
        JOIN t.usuarioMedicamento um
        JOIN um.medicamento m
        WHERE t.ativo = true
        AND um.usuario.id = :usuarioId
        AND te.horario < :agora
        AND te.status = 'ESQUECIDO'
        ORDER BY te.horario DESC
        """)
    List<EventoAtivoPorUsuarioDTO> findEventosAtrasadosPorUsuario(
            Long usuarioId, 
            LocalDateTime agora);

    @Query("""
        SELECT new com.remedius.remedius.DTOs.EventoAtivoPorUsuarioDTO(
            te.id,
            m.nome,
            m.laboratorio,
            te.horario,
            t.dosagem,
            um.cor,
            te.status
        )
        FROM TratamentoEventoEntity te
        JOIN te.tratamento t
        JOIN t.usuarioMedicamento um
        JOIN um.medicamento m
        WHERE t.ativo = true
        AND um.usuario.id = :usuarioId
        AND te.horario BETWEEN :inicio AND :fim
        ORDER BY te.horario ASC
        """)
    List<EventoAtivoPorUsuarioDTO> findEventosAtivosPorUsuario(
            Long usuarioId, 
            LocalDateTime inicio, 
            LocalDateTime fim);
            
    @Query("""
        SELECT te
        FROM TratamentoEventoEntity te
        JOIN FETCH te.tratamento t
        JOIN FETCH t.usuarioMedicamento um
        JOIN FETCH um.medicamento m
        WHERE te.id = :id
        """)
    Optional<TratamentoEventoEntity> findByIdWithRelationships(Long id);

    // Deletar eventos que tem o tratamento id = tratamentoId
    void deleteByTratamentoId(Long tratamentoId);
}