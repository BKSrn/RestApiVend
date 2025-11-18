package com.example.VEND.service;

import com.example.VEND.dto.UsuarioAdmResponseDTO;
import com.example.VEND.dto.UsuarioCadastrarDTO;
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

    public List<UsuarioAdmResponseDTO> listarTodos() {
        return converteToDTOList(repositorioUsuarioAdm.findAll());
    }

    public UsuarioAdmResponseDTO buscarPorId(Long id){
        Optional<UsuarioAdm> usuario = repositorioUsuarioAdm.findById(id);

        if (usuario.isPresent()){
            return converteToDTO(usuario.get());
        }
        return null;
    }

    public void cadastrarUsuario(UsuarioCadastrarDTO dto) {
        if (dto != null){
            repositorioUsuarioAdm.save(new UsuarioAdm(dto));
        }else {
            throw new RuntimeException("Informe todas as informações corretamentes");
        }

    }

    private UsuarioAdmResponseDTO converteToDTO(UsuarioAdm usuarioAdm){
        return new UsuarioAdmResponseDTO(usuarioAdm.getId(), usuarioAdm.getEmail(), usuarioAdm.getSenha());
    }

    private List<UsuarioAdmResponseDTO> converteToDTOList(List<UsuarioAdm> usuarios){
        return usuarios.stream()
                .map(u -> new UsuarioAdmResponseDTO(u.getId(), u.getEmail(), u.getSenha()))
                .collect(Collectors.toList());
    }


}
