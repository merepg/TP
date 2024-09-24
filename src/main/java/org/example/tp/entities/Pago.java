package org.example.tp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double monto;
    private String metodoPago;
    private Date fechaEmision;
    private Date fechaVencimiento;

    @OneToOne
    @JoinColumn(name="cita_id")
    private Cita cita;
    @ManyToOne
    @JoinColumn(name="estadoPago_id")
    private EstadoPago estadoPago;
    }

