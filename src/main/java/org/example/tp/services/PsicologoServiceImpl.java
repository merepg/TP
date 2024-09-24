package org.example.tp.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.dtos.PsicologoDTO;
import org.example.tp.entities.Psicologo;
import org.example.tp.entities.Usuario;
import org.example.tp.interfaces.PsicologoService;
import org.example.tp.repository.PsicologoRepository;
import org.example.tp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsicologoServiceImpl implements PsicologoService {
    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Psicologo> obtenerPsicologos() {
        return psicologoRepository.findAll();
    }

    @Transactional
    @Override
    public Psicologo registrar(PsicologoDTO psicologoDTO) {
        Usuario usuario = usuarioRepository.findById(psicologoDTO.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found"));

        Psicologo psicologo = new Psicologo();
        psicologo.setUsuario(usuario);
        psicologo.setValoracion(psicologoDTO.getValoracion());
        psicologo.setUrlCertificado(psicologoDTO.getUrlCertificado());
        psicologo.setAnios_experiencia(psicologoDTO.getAnios_experiencia());

        return psicologoRepository.save(psicologo);
    }

    @Transactional
    @Override
    public void eliminar(Integer id){
        psicologoRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Psicologo actualizar(Psicologo psicologo) {
        return psicologoRepository.findById(psicologo.getUsuario_id())
                .map(existingPsicologo -> {
                    existingPsicologo.setValoracion(psicologo.getValoracion());
                    existingPsicologo.setUrlCertificado(psicologo.getUrlCertificado());
                    existingPsicologo.setAnios_experiencia(psicologo.getAnios_experiencia());
                    return psicologoRepository.save(existingPsicologo);
                })
                .orElseThrow(() -> new EntityNotFoundException("Psicologo not found"));
    }

    // HU15
    @Override
    public List<PacienteDTO> ListarPacientesPorPsicologos(Long psicologoId){
        return psicologoRepository.ListarPacientesPorPsicologos(psicologoId);
    }

    // HU12
    @Override
    public List<CitaDTO> ListarCitasPorPsicologo(Long psicologoId){
        return psicologoRepository.ListarCitasPorPsicologo(psicologoId);
    }

    // US26
    @Override
    public List<PsicologoDTO> listarPsicologosOrdenadosPorValoracion(){
        return psicologoRepository.listarPsicologosOrdenadosPorValoracion();
    }

    // US26
    @Override
    public List<PsicologoDTO> listarPsicologosOrdenadosPorAnios(){
        return psicologoRepository.listarPsicologosOrdenadosPorAnios();
    }

    // US26
    @Override
    public List<PsicologoDTO> listarPsicologosPorRangoEdadPacientes(Integer edadMin, Integer edadMax){
        return psicologoRepository.listarPsicologosPorRangoEdadPacientes(edadMin, edadMax);
    }
}
