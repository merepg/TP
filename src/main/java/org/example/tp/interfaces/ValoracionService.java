package org.example.tp.interfaces;

import org.example.tp.dtos.ValoracionDTO;
import org.example.tp.entities.Valoracion;

import java.util.List;

public interface ValoracionService {
    public List<Valoracion> obtenerValoraciones();
    public Valoracion registrar(Valoracion valoracion);
    public void eliminar(Integer id);
    public Valoracion actualizar(Valoracion valoracion);

    void crearValoracion(ValoracionDTO valoracionDTO);
}
