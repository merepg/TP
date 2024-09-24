package org.example.tp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fechaCita;
    private Time hora;
    private String urlReunion;
    private String resultado;
    @ManyToOne
    @JoinColumn(name="psicologo_id")
    private Psicologo psicologo;
    @ManyToOne
    @JoinColumn (name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name="estadoCita_id")
    private EstadoCita estadoCita;
}
