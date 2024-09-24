package org.example.tp.services;

import jakarta.transaction.Transactional;
import org.example.tp.entities.Psicologo;
import org.example.tp.entities.Usuario;
import org.example.tp.interfaces.UsuarioService;
import org.example.tp.repository.PsicologoRepository;
import org.example.tp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    @Override
    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public void eliminar(Integer id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Usuario actualizar(Usuario usuario) {
        if(usuarioRepository.findById(usuario.getId()).isPresent()){
            return usuarioRepository.save(usuario);
        }
        return null;
    }
}
