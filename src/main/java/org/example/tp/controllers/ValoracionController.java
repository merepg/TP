package org.example.tp.controllers;

import org.example.tp.dtos.ValoracionDTO;
import org.example.tp.entities.Valoracion;
import org.example.tp.interfaces.ValoracionService;
import org.example.tp.services.ValoracionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ValoracionController {
    @Autowired
    private ValoracionServiceImpl valoracionServiceImpl;

    @GetMapping("/valoraciones")
    public List<ValoracionDTO> obtenerValoraciones(){
        ModelMapper modelMapper= new ModelMapper();
        List<Valoracion> valoraciones= valoracionServiceImpl.obtenerValoraciones();
        List<ValoracionDTO> valoracionDTOS= Arrays.asList(modelMapper.map(valoraciones, ValoracionDTO[].class));
        return valoracionDTOS;
    }

    @PostMapping("/valoracion")
    public ValoracionDTO registrar(@RequestBody ValoracionDTO valoracionDTO){
        ModelMapper mapper= new ModelMapper();
        Valoracion valoracion= mapper.map(valoracionDTO, Valoracion.class);
        valoracion=valoracionServiceImpl.registrar(valoracion);
        return mapper.map(valoracion, ValoracionDTO.class);
    }

    @DeleteMapping("/valoracion/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        try{
            valoracionServiceImpl.eliminar(id);
        }catch(Exception e){
            throw new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }

    @PutMapping("/valoracion")
    public ResponseEntity<ValoracionDTO> actualizar(@RequestBody ValoracionDTO valoracionDTO){
        ModelMapper mapper= new ModelMapper();
        try{
            Valoracion valoracion= mapper.map(valoracionDTO, Valoracion.class);
            valoracion=valoracionServiceImpl.actualizar(valoracion);
            valoracionDTO=mapper.map(valoracion, ValoracionDTO.class);
        }catch(Exception e){
            return new ResponseEntity<>(valoracionDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(valoracionDTO);
    }

    // HU02
    @PostMapping("/valoracion/HU02")
    public void crearValoracion(@RequestBody ValoracionDTO valoracionDTO) {
        valoracionServiceImpl.crearValoracion(valoracionDTO);
    }
}
