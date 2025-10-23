package com.example.VEND.controller;

import com.example.VEND.dto.UsuarioAdmDTO;
import com.example.VEND.service.UsuarioAdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioAdmController {

    @Autowired
    private UsuarioAdmService usuarioService;

    @GetMapping
    public List<UsuarioAdmDTO> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioAdmDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deletarUsuarioAdm(@PathVariable Long id){
        return usuarioService.deletarUsuario(id);
    }
}
