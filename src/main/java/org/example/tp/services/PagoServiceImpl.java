package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.dtos.PagoDTO;
import org.example.tp.entities.Pago;
import org.example.tp.interfaces.PagoService;
import org.example.tp.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;


    @Override
    public List<Pago> obtenerPagos() {
        return pagoRepository.findAll();
    }

    @Transactional
    @Override
    public Pago registrar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Pago actualizar(Pago pago) {
        if(pagoRepository.findById(pago.getId()).isPresent()){
            return pagoRepository.save(pago);
        }
        return null;
    }

    // US23: Seleccionar método de pago
    @Override
    public void actualizarMetodoPago(@Param("citaId") Long citaId, @Param("metodoPago") String metodoPago){
        pagoRepository.actualizarMetodoPago(citaId,metodoPago);
    }

    // HU29: Filtrado de pagos
    @Override
    public List<PagoDTO> listarPagosPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin){
        return pagoRepository.listarPagosPorRangoFechas(fechaInicio, fechaFin);
    }
}
