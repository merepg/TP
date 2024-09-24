package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.dtos.ValoracionDTO;
import org.example.tp.entities.Valoracion;
import org.example.tp.interfaces.ValoracionService;
import org.example.tp.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {
    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public List<Valoracion> obtenerValoraciones() {
        return valoracionRepository.findAll();
    }

    @Transactional
    @Override
    public Valoracion registrar(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        valoracionRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Valoracion actualizar(Valoracion valoracion) {
        if(valoracionRepository.findById(valoracion.getId()).isPresent()){
            valoracionRepository.save(valoracion);
        }
        return null;
    }

    @Override
    public void crearValoracion(ValoracionDTO valoracionDTO){
    }
}
