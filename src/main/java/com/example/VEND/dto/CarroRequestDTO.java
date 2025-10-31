package com.example.VEND.dto;

import com.example.VEND.model.Carroceria;
import io.swagger.v3.oas.annotations.media.Schema;

public record CarroRequestDTO(@Schema(description = "Modelo do carro", example = "Uno")
                              String modelo,

                              @Schema(description = "Marca do carro", example = "Fiat")
                              String marca,

                              @Schema(description = "Ano de fabricação do carro", example = "2023")
                              Integer ano,

                              @Schema(description = "Tipo de carroceria do carro", example = "HATCH")
                              Carroceria carroceria,

                              @Schema(description = "Preço do carro em reais", example = "45000.00")
                              Double preco
) {
}
