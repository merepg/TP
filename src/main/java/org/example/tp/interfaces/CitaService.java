package org.example.tp.interfaces;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Cita;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaService {
    public List<Cita> obtenercitas();
    public Cita registrar(Cita cita);
    public void eliminar(Integer id);
    public Cita actualizar(Cita cita);

    // US09: Marca de compleción de cita como paciente/psicólogo
    void marcarCitaCompletadaPorPaciente(@Param("citaId") Integer citaId, @Param("pacienteId") Integer pacienteId);

    // T20: Creación de función PUT sobre la entidad cita respecto a su atributo de estado de cita como psicólogo
    void marcarCitaCompletadaPorPsicologo(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId);

    // US14: Subir reporte de la cita elaborado por el psicólogo
    // T21: Creación de función PUT sobre la entidad cita que altera el campo de resultado
    void actualizarResultadoCita(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId, @Param("resultado") String resultado);

    // US19: Aceptación de cita
    void aceptarCita(@Param("citaId") Long citaId, @Param("psicologoId") Long psicologoId);
}
