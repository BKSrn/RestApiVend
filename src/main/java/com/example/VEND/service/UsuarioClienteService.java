package com.example.VEND.service;

import com.example.VEND.dto.UsuarioClienteCadastrarDTO;
import com.example.VEND.dto.UsuarioClienteLoginDTO;
import com.example.VEND.dto.UsuarioClienteResponseDTO;
import com.example.VEND.model.UsuarioCliente;
import com.example.VEND.repository.RepositorioUsuarioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteService {

    @Autowired
    private RepositorioUsuarioCliente repositorioUsuarioCliente;

    public UsuarioClienteResponseDTO cadastrar(UsuarioClienteCadastrarDTO dto) {
        UsuarioCliente usuarioExiste = repositorioUsuarioCliente.findByEmail(dto.email());

        if (usuarioExiste != null){
            throw new IllegalArgumentException("Este email ja foi cadastrado!");
        }
        UsuarioCliente usuarioSalvo = repositorioUsuarioCliente.save(new UsuarioCliente(dto));

        return new UsuarioClienteResponseDTO(dto.email(), dto.senha());
    }

    public UsuarioClienteResponseDTO login(UsuarioClienteLoginDTO dto) {
        UsuarioCliente usuario = repositorioUsuarioCliente.findByEmailAndSenha(dto.email(), dto.senha());
        return new UsuarioClienteResponseDTO(usuario.getEmail(), usuario.getSenha());
    }
}
