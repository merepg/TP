package org.example.tp.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.Cita;
import org.example.tp.entities.EstadoPago;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private Integer id;
    private Double monto;
    private String metodoPago;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private Cita cita;
    private EstadoPago estadoPago;
}
