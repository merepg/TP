package org.example.tp.controllers;

import org.example.tp.dtos.EstadoCitaDTO;
import org.example.tp.dtos.EstadoPagoDTO;
import org.example.tp.entities.EstadoPago;
import org.example.tp.interfaces.EstadoPagoService;
import org.example.tp.services.EstadoPagoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstadoPagoController {
    @Autowired
    private EstadoPagoServiceImpl estadoPagoServiceImpl;

    @GetMapping("/estado-pagos")
    public List<EstadoPagoDTO> obtenerEstadoPagos() {
        ModelMapper modelMapper = new ModelMapper();
        List<EstadoPago> estadoPago=estadoPagoServiceImpl.obtenerEstadoPagos();
        List<EstadoPagoDTO> estadoPagoDTOS= Arrays.asList(modelMapper.map(estadoPago, EstadoPagoDTO[].class));
        return estadoPagoDTOS;
    }

    @PostMapping("/estado-pago")
    public EstadoPagoDTO registrar(@RequestBody EstadoPagoDTO estadoPagoDTO) {
        ModelMapper mapper = new ModelMapper();
        EstadoPago estadoPago=mapper.map(estadoPagoDTO, EstadoPago.class);
        estadoPago=estadoPagoServiceImpl.registrar(estadoPago);
        return mapper.map(estadoPago, EstadoPagoDTO.class);
    }
    @DeleteMapping("/estado-pago/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        try {
            estadoPagoServiceImpl.eliminar(id);
        }catch(Exception e) {
            throw  new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }
    @PutMapping("estado-pago")
    public ResponseEntity<EstadoPagoDTO> actualizar (@RequestBody EstadoPagoDTO estadoPagoDTO) {
        ModelMapper mapper = new ModelMapper();
        try{
            EstadoPago estadoPago=mapper.map(estadoPagoDTO, EstadoPago.class);
            estadoPago=estadoPagoServiceImpl.actualizar(estadoPago);
            estadoPagoDTO=mapper.map(estadoPago, EstadoPagoDTO.class);
        } catch (Exception e) {
            return new ResponseEntity<>(estadoPagoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estadoPagoDTO);
    }


}
