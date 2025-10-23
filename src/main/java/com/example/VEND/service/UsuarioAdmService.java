package com.example.VEND.service;

import com.example.VEND.dto.UsuarioAdmDTO;
import com.example.VEND.model.UsuarioAdm;
import com.example.VEND.repository.RepositorioUsuarioAdm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioAdmService {

    @Autowired
    private RepositorioUsuarioAdm repositorioUsuarioAdm;

    public String deletarUsuario(Long id) {
        if (repositorioUsuarioAdm.existsById(id)){
            repositorioUsuarioAdm.deleteById(id);
            return ("Usuario " + id + " excluido com sucesso");
        }
        return null;
    }

    public List<UsuarioAdmDTO> listarTodos() {
        return converteToDTOList(repositorioUsuarioAdm.findAll());
    }

    public UsuarioAdmDTO buscarPorId(Long id){
        Optional<UsuarioAdm> usuario = repositorioUsuarioAdm.findById(id);

        if (usuario.isPresent()){
            return converteToDTO(usuario.get());
        }
        return null;
    }

    private UsuarioAdmDTO converteToDTO(UsuarioAdm usuarioAdm){
        return new UsuarioAdmDTO(usuarioAdm.getId(), usuarioAdm.getEmail(), usuarioAdm.getSenha());
    }

    private List<UsuarioAdmDTO> converteToDTOList(List<UsuarioAdm> usuarios){
        return usuarios.stream()
                .map(u -> new UsuarioAdmDTO(u.getId(), u.getEmail(), u.getSenha()))
                .collect(Collectors.toList());
    }
}
