package com.example.VEND.controller;

import com.example.VEND.dto.UsuarioClienteCadastrarDTO;
import com.example.VEND.dto.UsuarioClienteLoginDTO;
import com.example.VEND.dto.UsuarioClienteResponseDTO;
import com.example.VEND.service.UsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class UsuarioClienteController {

    @Autowired
    private UsuarioClienteService usuarioClienteService;

    @PostMapping
    public ResponseEntity<UsuarioClienteResponseDTO> cadastrar(@RequestBody UsuarioClienteCadastrarDTO dto){
        try {
            UsuarioClienteResponseDTO usuarioCliente = usuarioClienteService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioCliente);

        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping
    public ResponseEntity<UsuarioClienteResponseDTO> login(@RequestBody UsuarioClienteLoginDTO dto){
        try {
            UsuarioClienteResponseDTO usuarioCliente = usuarioClienteService.login(dto);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioCliente);

        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
