package org.example.tp.repository;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.dtos.PsicologoDTO;
import org.example.tp.entities.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {

    // HU15
    // Como psicólogo, quiero ser capaz de ver la información de mis pacientes,
    // para así poder darles un tratamiento personalizado y correcto.
    @Query("SELECT new org.example.tp.dtos.PacienteDTO(c.paciente.usuario, c.paciente.direccion, c.paciente.distrito) " +
            "FROM Cita c " +
            "WHERE c.psicologo.usuario_id = :psicologoId")
    List<PacienteDTO> ListarPacientesPorPsicologos(@Param("psicologoId") Long psicologoId);

    // HU12
    // Como paciente, deseo poder revisar la información de la cita
    // pendiente, para así poder confirmar detalles previamente.
    @Query("SELECT new org.example.tp.dtos.CitaDTO(c.id, c.fechaCita, c.hora, c.urlReunion, c.resultado, c.psicologo, c.paciente, c.estadoCita) " +
            "FROM Cita c " +
            "WHERE c.psicologo.usuario.id = :psicologoId AND c.estadoCita.id = 1")
    List<CitaDTO> ListarCitasPorPsicologo(@Param("psicologoId") Long psicologoId);

    // HU26: Filtrar psicólogos
    // Ordenar por valoracion DESCENDENTE
    @Query("SELECT new org.example.tp.dtos.PsicologoDTO(p.usuario, p.valoracion, p.urlCertificado, p.anios_experiencia) " +
            "FROM Psicologo p  " +
            "WHERE p.usuario.tipo_entidad = true " +
            "ORDER BY p.valoracion DESC")
    List<PsicologoDTO> listarPsicologosOrdenadosPorValoracion();

    // HU26: Filtrar psicólogos
    // Ordenar por anios de exp DESCENDENTE
    @Query("SELECT new org.example.tp.dtos.PsicologoDTO(p.usuario, p.valoracion, p.urlCertificado, p.anios_experiencia) " +
            "FROM Psicologo p  WHERE p.usuario.tipo_entidad = true " +
            "ORDER BY p.anios_experiencia DESC")
    List<PsicologoDTO> listarPsicologosOrdenadosPorAnios();

    // HU26: Filtrar psicologos
    // Por edad
    @Query(value = "SELECT new org.example.tp.dtos.PsicologoDTO(p.usuario, p.valoracion, p.urlCertificado, p.anios_experiencia) " +
            "FROM Psicologo p  " +
            "WHERE p.usuario.tipo_usuario = true " +
            "AND EXTRACT(YEAR FROM AGE(CURRENT_DATE, p.usuario.fecha_nacimiento)) BETWEEN :edadMin AND :edadMax", nativeQuery = true)
    List<PsicologoDTO> listarPsicologosPorRangoEdadPacientes(@Param("edadMin") Integer edadMin, @Param("edadMax") Integer edadMax);
}
