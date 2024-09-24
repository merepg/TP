package org.example.tp.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Paciente;
import org.example.tp.entities.Usuario;
import org.example.tp.interfaces.PacienteService;
import org.example.tp.repository.PacienteRepository;
import org.example.tp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    @Transactional
    @Override
    public Paciente registrar(PacienteDTO pacienteDTO) {
        Usuario usuario = usuarioRepository.findById(pacienteDTO.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found"));

        Paciente paciente = new Paciente();
        paciente.setUsuario(usuario);
        paciente.setDistrito(pacienteDTO.getDistrito());
        paciente.setDireccion(pacienteDTO.getDireccion());

        return pacienteRepository.save(paciente);
    }
    @Transactional
    @Override
    public void eliminar(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Paciente actualizar(Paciente paciente) {
        return pacienteRepository.findById(paciente.getUsuario_id())
                .map(existingPaciente -> {
                    existingPaciente.setDistrito(paciente.getDistrito());
                    existingPaciente.setDireccion(paciente.getDireccion());
                    return pacienteRepository.save(existingPaciente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Paciente not found"));
    }

    // HU19
    @Override
    public List<CitaDTO> ListarCitasProximasPorPaciente(Long pacienteId){
        return pacienteRepository.ListarCitasProximasPorPaciente(pacienteId);
    }

    // HU12
    @Override
    public List<CitaDTO> ListarCitasPorPaciente(Long pacienteId){
        return pacienteRepository.ListarCitasPorPaciente(pacienteId);
    }
}
