package org.example.tp.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tp.entities.Cita;
import org.example.tp.entities.Psicologo;
import org.example.tp.entities.Usuario;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PsicologoDTO {

    private Usuario usuario;
    private double valoracion;
    private String urlCertificado;
    private int anios_experiencia;
}
