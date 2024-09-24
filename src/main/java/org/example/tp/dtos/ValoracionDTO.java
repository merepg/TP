package org.example.tp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.Cita;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionDTO {
    private Integer id;
    private String comentario;
    private Double puntuacion;
    private Cita cita;
}
