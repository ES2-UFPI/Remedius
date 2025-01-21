package com.remedius.remedius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.remedius.remedius.entities.TratamentoEntity;

@Repository
public interface TratamentoRepository extends JpaRepository<TratamentoEntity, Long> {

    @Query("SELECT t FROM TratamentoEntity t WHERE t.usuarioMedicamento.id = :usuarioMedicamentoId")
    List<TratamentoEntity> findByUsuarioMedicamentoId(Long usuarioMedicamentoId);

    boolean existsByUsuarioMedicamentoIdAndAtivoTrue(Long usuarioMedicamentoId);

}
