package org.example.tp.controllers;

import org.example.tp.dtos.EstadoCitaDTO;
import org.example.tp.entities.EstadoCita;
import org.example.tp.interfaces.EstadoCitaService;
import org.example.tp.services.EstadoCitaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstadoCitaController {
    @Autowired
    private EstadoCitaServiceImpl estadoCitaServiceImpl;

    @GetMapping("/estado-citas")
    public List<EstadoCitaDTO> obtenerEstadoCitas() {
        ModelMapper modelMapper = new ModelMapper();
        List<EstadoCita> estadoCitas=estadoCitaServiceImpl.obtenerEstadoCitas();
        List<EstadoCitaDTO> estadoCitaDTOS= Arrays.asList(modelMapper.map(estadoCitas, EstadoCitaDTO[].class));
        return estadoCitaDTOS;
    }


    @PostMapping("/estado-cita")
    public EstadoCitaDTO registrar(@RequestBody EstadoCitaDTO estadoCitaDTO) {
        ModelMapper mapper = new ModelMapper();
        EstadoCita estadoCita = mapper.map(estadoCitaDTO, EstadoCita.class);
        estadoCita=estadoCitaServiceImpl.registrar(estadoCita);
        return mapper.map(estadoCita, EstadoCitaDTO.class);
    }

    @DeleteMapping("/estado-cita/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        try {
            estadoCitaServiceImpl.eliminar(id);
        }catch (Exception e) {
            throw new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }
    @PutMapping("/estado-cita")
    public ResponseEntity<EstadoCitaDTO> actualizar(@RequestBody EstadoCitaDTO estadoCitaDTO) {
        ModelMapper mapper = new ModelMapper();
        try{
            EstadoCita estadoCita = mapper.map(estadoCitaDTO, EstadoCita.class);
            estadoCita=estadoCitaServiceImpl.actualizar(estadoCita);
            estadoCitaDTO=mapper.map(estadoCita, EstadoCitaDTO.class);
        } catch (Exception e) {
            return new ResponseEntity<>(estadoCitaDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estadoCitaDTO);
    }
}
