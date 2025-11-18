package com.example.VEND.controller;

import com.example.VEND.dto.CarroCadastrarDTO;
import com.example.VEND.dto.CarroResponseDTO;
import com.example.VEND.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
@Tag(name = "Carros", description = "Endpoints para gerenciamento de carros no sistema")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<String> cadastarCarro(@RequestBody CarroCadastrarDTO dto){
        try {
            carroService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar todos os carros",
            description = "Retorna uma lista com todos os carros cadastrados no sistema, ordenados por preço crescente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de carros retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listarCarros() {
        List<CarroResponseDTO> carros = carroService.listarTodos();
        return ResponseEntity.ok(carros);
    }

    @Operation(
            summary = "Buscar carro por ID",
            description = "Retorna os detalhes de um carro específico com base no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carro encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carro não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(
            @Parameter(description = "ID do carro a ser buscado", required = true, example = "1")
            @PathVariable Long id) {
        CarroResponseDTO carro = carroService.buscarPorId(id);

        if (carro != null) {
            return ResponseEntity.ok(carro);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Buscar carros por marca",
            description = "Retorna uma lista de carros filtrados pela marca especificada, ordenados por preço"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de carros retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nenhum carro encontrado para a marca especificada",
                    content = @Content
            )
    })
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<CarroResponseDTO>> buscarCarrosPorMarca(
            @Parameter(description = "Marca do carro (ex: fiat, volkswagen, chevrolet)", required = true, example = "fiat")
            @PathVariable String marca) {
        List<CarroResponseDTO> carros = carroService.buscarPorMarca(marca);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carros);
    }

    @Operation(
            summary = "Buscar carros por marca e modelo",
            description = "Retorna uma lista de carros filtrados por marca e modelo, ordenados por preço crescente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de carros retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarroResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nenhum carro encontrado com os critérios especificados",
                    content = @Content
            )
    })
    @GetMapping("/marca/{marca}/modelo/{modelo}")
    public ResponseEntity<List<CarroResponseDTO>> buscarCarrosPorMarcaModelo(
            @Parameter(description = "Marca do carro", required = true, example = "fiat")
            @PathVariable String marca,
            @Parameter(description = "Modelo do carro", required = true, example = "uno")
            @PathVariable String modelo) {
        List<CarroResponseDTO> carros = carroService.buscarPorMarcaModelo(marca, modelo);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carros);
    }

    @Operation(
            summary = "Deletar um carro",
            description = "Remove um carro do sistema com base no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carro deletado com sucesso",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carro não encontrado",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCarro(
            @Parameter(description = "ID do carro a ser deletado", required = true, example = "1")
            @PathVariable Long id) {
        String resultado = carroService.deletarCarro(id);

        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Carro não encontrado com o ID: " + id);
    }
}