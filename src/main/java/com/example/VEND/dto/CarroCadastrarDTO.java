package com.example.VEND.dto;

import jakarta.validation.constraints.NotBlank;

public record CarroCadastrarDTO(@NotBlank String carroceria,
                                @NotBlank byte[] imagem,
                                @NotBlank String modelo,
                                @NotBlank Integer ano,
                                @NotBlank Double preco,
                                @NotBlank String marca) {
}
