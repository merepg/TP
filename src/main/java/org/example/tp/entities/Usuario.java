package org.example.tp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.BitSet;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean tipo_usuario;
    @Column(nullable = true)
    @Nullable
    private Boolean tipo_entidad;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date fecha_nacimiento;
    private String email;
    private String password;
    private String sexo;

}
