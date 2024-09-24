package org.example.tp.interfaces;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Paciente;

import java.util.List;

public interface PacienteService {
    public List<Paciente> obtenerPacientes();
    public Paciente registrar(PacienteDTO pacienteDTO);
    public void eliminar(Integer id);
    public Paciente actualizar(Paciente paciente);

    // HU19
    List<CitaDTO> ListarCitasProximasPorPaciente(Long pacienteId);

    // HU12
    List<CitaDTO> ListarCitasPorPaciente(Long pacienteId);
}
