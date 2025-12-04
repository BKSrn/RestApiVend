package com.example.VEND.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioClienteCadastrarDTO(@NotBlank @Email String email,
                                         @NotBlank String senha) {
}
