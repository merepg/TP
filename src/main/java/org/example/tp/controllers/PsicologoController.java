package org.example.tp.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.dtos.PsicologoDTO;
import org.example.tp.entities.Psicologo;
import org.example.tp.interfaces.PsicologoService;
import org.example.tp.services.PsicologoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PsicologoController {
    @Autowired
    private PsicologoServiceImpl psicologoServiceImpl;

    @GetMapping("/psicologos")
    public List<PsicologoDTO> obtenerPsicologos() {
        ModelMapper modelMapper = new ModelMapper();
        List<Psicologo> psicologos= psicologoServiceImpl.obtenerPsicologos();
        List<PsicologoDTO> psicologoDTOS = Arrays.asList(modelMapper.map(psicologos, PsicologoDTO[].class));
        return psicologoDTOS;
    }
    @PostMapping("/psicologo")
    public ResponseEntity<PsicologoDTO> registrar(@RequestBody PsicologoDTO psicologoDTO) {
        try {
            Psicologo psicologo = psicologoServiceImpl.registrar(psicologoDTO);
            PsicologoDTO responseDTO = new PsicologoDTO(
                    psicologo.getUsuario(),
                    psicologo.getValoracion(),
                    psicologo.getUrlCertificado(),
                    psicologo.getAnios_experiencia()
            );
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/psicologo/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        try{
            psicologoServiceImpl.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el Psicologo");
        }
    }

    @PutMapping("/psicologo")
    public ResponseEntity<PsicologoDTO> actualizar(@RequestBody PsicologoDTO psicologoDTO){
        ModelMapper mapper = new ModelMapper();
        try{
            Psicologo psicologo= mapper.map(psicologoDTO, Psicologo.class);
            psicologo= psicologoServiceImpl.actualizar(psicologo);
            psicologoDTO= mapper.map(psicologo,PsicologoDTO.class);
        } catch (Exception e) {
            return new ResponseEntity<>(psicologoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(psicologoDTO);
    }

    //HU15
    @GetMapping("/listPacientesPorPsicologo")
    List<PacienteDTO> ListarPacientesPorPsicologos(@RequestParam("idPsicologo") Long idPsicologo){
        return psicologoServiceImpl.ListarPacientesPorPsicologos(idPsicologo);
    }

    // HU12
    @GetMapping("/listCitasPorPsicologo")
    List<CitaDTO> ListarCitasPorPsicologo (@RequestParam("idPsicologo") Long idPsicologo){
        return psicologoServiceImpl.ListarCitasPorPsicologo(idPsicologo);
    }

    // HU26
    @GetMapping("/ordenados-por-valoracion")
    public List<PsicologoDTO> listarPsicologosOrdenadosPorValoracion() {
        return psicologoServiceImpl.listarPsicologosOrdenadosPorValoracion();
    }

    // HU26
    @GetMapping("/ordenados-por-aniosExp")
    List<PsicologoDTO> listarPsicologosOrdenadosPorAniosExp() {
        return psicologoServiceImpl.listarPsicologosOrdenadosPorAnios();
    }

    // HU26
    @GetMapping("/por-rango-edad-pacientes")
    public List<PsicologoDTO> listarPsicologosPorRangoEdadPacientes(@RequestParam Integer edadMin, @RequestParam Integer edadMax) {
        return  psicologoServiceImpl.listarPsicologosPorRangoEdadPacientes(edadMin, edadMax);
    }

}
