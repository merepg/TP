package org.example.tp.controllers;

import org.example.tp.dtos.PagoDTO;
import org.example.tp.entities.Pago;
import org.example.tp.interfaces.PagoService;
import org.example.tp.services.PagoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PagoController {
    @Autowired
    private PagoServiceImpl pagoServiceImpl;

    @GetMapping("/pagos")
    public List<PagoDTO> obtenerPagos() {
        ModelMapper modelMapper= new ModelMapper();
        List<Pago> pagos= pagoServiceImpl.obtenerPagos();
        List<PagoDTO> pagoDTOS= Arrays.asList(modelMapper.map(pagos, PagoDTO[].class));
        return pagoDTOS;
    }
    @PostMapping("/pago")
    public PagoDTO registrar(@RequestBody PagoDTO pagoDTO) {
        ModelMapper mapper= new ModelMapper();
        Pago pago= mapper.map(pagoDTO, Pago.class);
        pago= pagoServiceImpl.registrar(pago);
        return mapper.map(pago, PagoDTO.class);
    }

    @DeleteMapping("/pago/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        try{
            pagoServiceImpl.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Disculpe la molestia, no se ha podido eliminar");
        }
    }

    @PutMapping("/pago")
    public ResponseEntity<PagoDTO> actualizar(@RequestBody PagoDTO pagoDTO) {
        ModelMapper mapper= new ModelMapper();
        try{
            Pago pago= mapper.map(pagoDTO, Pago.class);
            pago= pagoServiceImpl.actualizar(pago);
            pagoDTO= mapper.map(pago, PagoDTO.class);
        } catch (Exception e) {
            return new ResponseEntity<>(pagoDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pagoDTO);
    }

    // HU23
    @PutMapping("/{citaId}/actualiza-metodo-pago")
    public void actualizarMetodoPago(@PathVariable Long citaId, @RequestBody String metodoPago) {
        pagoServiceImpl.actualizarMetodoPago(citaId, metodoPago);
    }

    // HU29
    @GetMapping("pagos/por-rango-fechas")
    public List<PagoDTO> listarPagosPorRangoFechas(@RequestParam("fechaInicio")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fechaInicio,
                                                   @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fechaFin) {
        return pagoServiceImpl.listarPagosPorRangoFechas(fechaInicio, fechaFin);
    }
}
