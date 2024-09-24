package org.example.tp.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.example.tp.dtos.CitaDTO;
import org.example.tp.dtos.PacienteDTO;
import org.example.tp.entities.Cita;
import org.example.tp.entities.Paciente;
import org.example.tp.services.PacienteServiceImpl;
import org.example.tp.services.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PacienteController {
    @Autowired
    private PacienteServiceImpl pacienteServiceImpl;
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/pacientes")
    public List<PacienteDTO> obtenerPacientes() {
        ModelMapper modelMapper= new ModelMapper();
        List<Paciente> pacientes= pacienteServiceImpl.obtenerPacientes();
        List<PacienteDTO> pacienteDTOS= Arrays.asList(modelMapper.map(pacientes, PacienteDTO[].class));
        return pacienteDTOS;
    }


    @PostMapping("/paciente")
    public ResponseEntity<PacienteDTO> registrar(@RequestBody PacienteDTO pacienteDTO) {
        try {
            Paciente paciente = pacienteServiceImpl.registrar(pacienteDTO);
            PacienteDTO responseDTO = new PacienteDTO(
                    paciente.getUsuario(),
                    paciente.getDistrito(),
                    paciente.getDireccion()
            );
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/paciente/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        try{
            pacienteServiceImpl.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }

    @PutMapping("/paciente")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO pacienteDTO) {
        ModelMapper mapper= new ModelMapper();
        try{
            Paciente paciente= mapper.map(pacienteDTO, Paciente.class);
            paciente= pacienteServiceImpl.actualizar(paciente);
            pacienteDTO= mapper.map(paciente, PacienteDTO.class);
        }catch (Exception e) {
            return new ResponseEntity<>(pacienteDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pacienteDTO);

    }

    //HU19
    @GetMapping("/listCitasProximas")
    List<CitaDTO> ListarCitasProximasPorPaciente(@RequestParam("idPaciente") Long idPaciente){
        return pacienteServiceImpl.ListarCitasProximasPorPaciente(idPaciente);
    }

    // HU12
    @GetMapping("/listCitasPendientesPorPaciente")
    List<CitaDTO> ListarCitasPorPaciente (@RequestParam("idPaciente") Long idPaciente){
        return pacienteServiceImpl.ListarCitasPorPaciente(idPaciente);
    }
}
