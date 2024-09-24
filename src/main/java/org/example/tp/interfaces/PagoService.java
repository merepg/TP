package org.example.tp.interfaces;

import org.example.tp.dtos.PagoDTO;
import org.example.tp.entities.Pago;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PagoService {
    public List<Pago> obtenerPagos();
    public Pago registrar(Pago pago);
    public void eliminar(Integer id);
    public Pago actualizar(Pago pago);

    // US23: Seleccionar método de pago
    void actualizarMetodoPago(@Param("citaId") Long citaId, @Param("metodoPago") String metodoPago);

    // HU29: Filtrado de pagos
    List<PagoDTO> listarPagosPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
