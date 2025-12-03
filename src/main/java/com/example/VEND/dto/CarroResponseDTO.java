
package com.example.VEND.dto;

import com.example.VEND.model.enums.Carroceria;
import com.example.VEND.model.UsuarioCliente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de transferência de dados para informações de carros")
public record CarroResponseDTO(
        @Schema(description = "ID único do carro", example = "1")
        Long id,

        @Schema(description = "Modelo do carro", example = "Uno")
        String modelo,

        @Schema(description = "Marca do carro", example = "Fiat")
        String marca,

        @Schema(description = "Ano de fabricação do carro", example = "2023")
        Integer ano,

        @Schema(description = "Tipo de carroceria do carro", example = "HATCH")
        Carroceria carroceria,

        @Schema(description = "Preço do carro em reais", example = "45000.00")
        Double preco,

        @Schema(description = "Imagem do carro")
        byte[] imagem,

        @Schema(description = "Cliente interessado no carro")
        UsuarioCliente usuarioClienteId
) {
}