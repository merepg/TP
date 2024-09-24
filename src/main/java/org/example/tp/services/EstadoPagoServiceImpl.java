package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.entities.EstadoPago;
import org.example.tp.interfaces.EstadoPagoService;
import org.example.tp.repository.EstadoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoPagoServiceImpl implements EstadoPagoService {
    @Autowired
    private EstadoPagoRepository estadoPagoRepository;


    @Override
    public List<EstadoPago> obtenerEstadoPagos() {
        return estadoPagoRepository.findAll();
    }

    @Transactional
    @Override
    public EstadoPago registrar(EstadoPago estadoPago) {
        return estadoPagoRepository.save(estadoPago);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        estadoPagoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public EstadoPago actualizar(EstadoPago estadoPago) {
        if(estadoPagoRepository.findById(estadoPago.getId()).isPresent()) {
            return estadoPagoRepository.save(estadoPago);
        }
        return null;
    }
}
