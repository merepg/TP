package org.example.tp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Psicologo {

    @Id
    private Integer usuario_id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private double valoracion;
    private String urlCertificado;
    private int anios_experiencia;

}
