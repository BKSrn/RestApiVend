package com.example.VEND.dto;

import com.example.VEND.model.Carroceria;
import com.example.VEND.model.UsuarioCliente;

public record CarroDTO(Long id,
                       String modelo,
                       String marca,
                       Integer ano,
                       Carroceria carroceria,
                       Double preco,
                       UsuarioCliente usuarioClienteId) {
}
