package com.example.VEND.controller;

import com.example.VEND.dto.UsuarioClienteCadastrarDTO;
import com.example.VEND.dto.UsuarioClienteLoginDTO;
import com.example.VEND.dto.UsuarioClienteResponseDTO;
import com.example.VEND.service.UsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuariosCliente")
public class UsuarioClienteController {

    @Autowired
    private UsuarioClienteService usuarioClienteService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioClienteCadastrarDTO dto){
        try {
            UsuarioClienteResponseDTO usuarioCliente = usuarioClienteService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro do email: " + usuarioCliente.email() + "realizado");

        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioClienteResponseDTO> login(@RequestBody UsuarioClienteLoginDTO dto) {
        try {
            UsuarioClienteResponseDTO usuarioCliente = usuarioClienteService.login(dto);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioCliente);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioClienteResponseDTO>> listarClientes(){
        try {
            List<UsuarioClienteResponseDTO> usuariosResponse = usuarioClienteService.buscarClientes();
            return ResponseEntity.status(HttpStatus.OK).body(usuariosResponse);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
