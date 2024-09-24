package org.example.tp.repository;

import org.example.tp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
}
