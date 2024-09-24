package org.example.tp.repository;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    // HU09: Marca de compleción de cita como paciente/psicólogo
    // Creación de función PUT sobre la entidad cita respecto a su atributo de estado de cita como paciente
    @Modifying
    @Query("UPDATE Cita c SET c.estadoCita.id = 4 WHERE c.id = :citaId AND c.paciente.usuario_id = :pacienteId")
    void marcarCitaCompletadaPorPaciente(@Param("citaId") Integer citaId, @Param("pacienteId") Integer pacienteId);

    // HU09: Marca de compleción de cita como paciente/psicólogo
    // Creación de función PUT sobre la entidad cita respecto a su atributo de estado de cita como psicólogo
    @Modifying
    @Query("UPDATE Cita c SET c.estadoCita.id = 4 WHERE c.id = :citaId AND c.psicologo.usuario_id = :psicologoId")
    void marcarCitaCompletadaPorPsicologo(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId);

    // HU14: Subir reporte de la cita elaborado por el psicólogo
    // Creación de función PUT sobre la entidad cita que altera el campo de resultado
    @Modifying
    @Query("UPDATE Cita c SET c.resultado = :resultado WHERE c.id = :citaId AND c.psicologo.usuario_id = :psicologoId")
    void actualizarResultadoCita(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId, @Param("resultado") String resultado);

    // HU19: Aceptación de cita
    // Creación de función PUT sobre una cita como psicólogo
    @Modifying
    @Query("UPDATE Cita c SET c.estadoCita.id = 2 WHERE c.id = :citaId AND c.psicologo.usuario_id = :psicologoId")
    void aceptarCita(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId);

}
