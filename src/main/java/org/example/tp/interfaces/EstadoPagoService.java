package org.example.tp.interfaces;

import org.example.tp.entities.EstadoPago;

import java.util.List;

public interface EstadoPagoService {
    public List<EstadoPago> obtenerEstadoPagos();
    public EstadoPago registrar(EstadoPago estadoPago);
    public void eliminar(Integer id);
    public EstadoPago actualizar(EstadoPago estadoPago);
}
