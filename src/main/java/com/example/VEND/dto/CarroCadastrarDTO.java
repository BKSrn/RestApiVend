package com.example.VEND.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarroCadastrarDTO(@NotBlank String carroceria,
                                @NotNull byte[] imagem,
                                @NotBlank String modelo,
                                @NotNull Integer ano,
                                @NotNull Double preco,
                                @NotBlank String marca) {
}
