package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.entities.EstadoCita;
import org.example.tp.interfaces.EstadoCitaService;
import org.example.tp.repository.EstadoCitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCitaServiceImpl  implements EstadoCitaService {
    @Autowired
    private EstadoCitaRepository estadoCitaRepository;


    @Override
    public List<EstadoCita> obtenerEstadoCitas() {
        return estadoCitaRepository.findAll();
    }

    @Transactional
    @Override
    public EstadoCita registrar(EstadoCita estadoCita) {
        return estadoCitaRepository.save(estadoCita);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        estadoCitaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public EstadoCita actualizar(EstadoCita estadoCita) {
        if(estadoCitaRepository.findById(estadoCita.getId()).isPresent()) {
            return estadoCitaRepository.save(estadoCita);
        }
        return null;
    }
}
