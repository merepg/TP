package org.example.tp.interfaces;

import org.example.tp.entities.Psicologo;
import org.example.tp.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> obtenerUsuarios();
    public Usuario registrar(Usuario usuario);
    public void eliminar(Integer id);
    public Usuario actualizar (Usuario usuario);
}
