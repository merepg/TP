package org.example.tp.interfaces;

import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.dtos.PsicologoDTO;
import org.example.tp.entities.Psicologo;

import java.util.List;

public interface PsicologoService {
    public List<Psicologo> obtenerPsicologos();
    public Psicologo registrar(PsicologoDTO psicologoDTO);
    public void eliminar(Integer id);
    public Psicologo actualizar (Psicologo psicologo);

    // HU15
    List<PacienteDTO> ListarPacientesPorPsicologos(Long psicologoId);

    //HU12
    List<CitaDTO> ListarCitasPorPsicologo(Long psicologoId);

    // US26
    List<PsicologoDTO> listarPsicologosOrdenadosPorValoracion();

    // US26
    List<PsicologoDTO> listarPsicologosOrdenadosPorAnios();

    // US26
    List<PsicologoDTO> listarPsicologosPorRangoEdadPacientes(Integer edadMin, Integer edadMax);
}
