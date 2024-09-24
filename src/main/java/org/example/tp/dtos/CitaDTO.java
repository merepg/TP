package org.example.tp.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.EstadoCita;
import org.example.tp.entities.Paciente;
import org.example.tp.entities.Psicologo;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CitaDTO {
    private Integer id;
    private Date fechaCita;
    private Time hora;
    private String urlReunion;
    private String resultado;
    private Psicologo psicologo;
    private Paciente paciente;
    private EstadoCita estadoCita;
}

