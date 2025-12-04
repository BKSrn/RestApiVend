package com.example.VEND.controller;

import com.example.VEND.dto.UsuarioAdmResponseDTO;
import com.example.VEND.dto.UsuarioCadastrarDTO;
import com.example.VEND.service.UsuarioAdmService;
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
@RequestMapping("/usuariosAdm")
@Tag(name = "Usuários Administradores", description = "Endpoints para gerenciamento de usuários administradores do sistema")
public class UsuarioAdmController {

    @Autowired
    private UsuarioAdmService usuarioService;

    @PostMapping
    public void cadastrarUsuario(@RequestBody UsuarioCadastrarDTO dto){
        try {
            usuarioService.cadastrarUsuario(dto);
            ResponseEntity.ok();
        }catch (RuntimeException e){
            ResponseEntity.badRequest();
        }

    }

    @Operation(
            summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuários administradores cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de usuários retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioAdmResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<List<UsuarioAdmResponseDTO>> listarUsuarios() {
        List<UsuarioAdmResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os detalhes de um usuário administrador específico com base no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioAdmResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdmResponseDTO> buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true, example = "1")
            @PathVariable Long id) {
        UsuarioAdmResponseDTO usuario = usuarioService.buscarPorId(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Deletar um usuário",
            description = "Remove um usuário administrador do sistema com base no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário deletado com sucesso",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuarioAdm(
            @Parameter(description = "ID do usuário a ser deletado", required = true, example = "1")
            @PathVariable Long id) {
        String resultado = usuarioService.deletarUsuario(id);

        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuário não encontrado com o ID: " + id);
    }
}