package com.example.VEND.repository;

import com.example.VEND.model.UsuarioCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarioCliente extends JpaRepository<UsuarioCliente, Long> {

    UsuarioCliente findByEmail(@NotNull @Email String email);

    UsuarioCliente findByEmailAndSenha(@NotBlank @Email String email, @NotBlank String senha);
}
