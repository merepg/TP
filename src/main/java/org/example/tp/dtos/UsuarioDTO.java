package org.example.tp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.BitSet;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private Boolean tipo_usuario;
    private Boolean tipo_entidad;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Date fecha_nacimiento;
    private String email;
    private String password;
    private String sexo;
}
