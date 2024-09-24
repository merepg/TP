package org.example.tp.interfaces;

import org.example.tp.entities.EstadoCita;

import java.util.List;

public interface EstadoCitaService {
    public List<EstadoCita> obtenerEstadoCitas();
    public EstadoCita registrar(EstadoCita estadoCita);
    public void eliminar(Integer id);
    public EstadoCita actualizar(EstadoCita estadoCita);
}
