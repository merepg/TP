package org.example.tp.controllers;

import org.example.tp.dtos.UsuarioDTO;
import org.example.tp.entities.Usuario;
import org.example.tp.interfaces.UsuarioService;
import org.example.tp.services.CitaServiceImpl;
import org.example.tp.services.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private CitaServiceImpl citaServiceImpl;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> obtenerUsuarios() {
        ModelMapper modelMapper = new ModelMapper();
        List<Usuario> usuarios = usuarioServiceImpl.obtenerUsuarios();
        List<UsuarioDTO> usuariosDTO = Arrays.asList(modelMapper.map(usuarios, UsuarioDTO[].class));
        return usuariosDTO;
    }

    @PostMapping("/usuario")
    public UsuarioDTO registrar(@RequestBody UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario = usuarioServiceImpl.registrar(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @DeleteMapping("/usuario/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        try{
            usuarioServiceImpl.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Disculpe la molestia, no es posible la eliminacion");
        }
    }

    @PutMapping("/usuario")
    public ResponseEntity<UsuarioDTO> actualizar(@RequestBody UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
            usuario = usuarioServiceImpl.actualizar(usuario);
            usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>(usuarioDTO, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioDTO);
    }
}
