package org.example.tp.repository;

import org.example.tp.dtos.PagoDTO;
import org.example.tp.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // HU23: Seleccionar método de pago
    // T25: Incluir campo de método de pago en la US24
    @Modifying
    @Query(value = "UPDATE Pago p SET p.metodoPago = :metodoPago WHERE p.cita.id = :citaId", nativeQuery = true)
    void actualizarMetodoPago(@Param("citaId") Long citaId, @Param("metodoPago") String metodoPago);

    // HU29: Filtrado de pagos
    // Por un rango de fechas
    @Query("SELECT new org.example.tp.dtos.PagoDTO(p.id, p.monto, p.metodoPago, p.fechaEmision, p.fechaVencimiento, p.cita, p.estadoPago) FROM Pago p WHERE DATE(p.fechaEmision) BETWEEN :fechaInicio AND :fechaFin")
    List<PagoDTO> listarPagosPorRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                            @Param("fechaFin") LocalDate fechaFin);
}
