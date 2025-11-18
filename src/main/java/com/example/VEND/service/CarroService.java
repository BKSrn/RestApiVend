package com.example.VEND.service;

import com.example.VEND.dto.CarroCadastrarDTO;
import com.example.VEND.dto.CarroResponseDTO;
import com.example.VEND.model.Carro;
import com.example.VEND.repository.RepositorioCarro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private RepositorioCarro repositorioCarro;

    private CarroResponseDTO converteToDTO(Carro carro) {
        return new CarroResponseDTO(carro.getId(), carro.getModelo(), carro.getMarca(), carro.getAno(), carro.getCarroceria(), carro.getPreco(), carro.getUsuarioCliente());
    }

    private List<CarroResponseDTO> converteToDTOList(List<Carro> carros) {
        return carros.stream()
                .map(c -> new CarroResponseDTO(c.getId(), c.getModelo(), c.getMarca(), c.getAno(), c.getCarroceria(), c.getPreco(), c.getUsuarioCliente()))
                .collect(Collectors.toList());
    }

    public CarroResponseDTO buscarPorId(Long id) {
        Optional<Carro> carro = repositorioCarro.findById(id);

        if (carro.isPresent()){
            return converteToDTO(carro.get());
        }else {
            return null;
        }
    }

    public String deletarCarro(Long id) {
        if (repositorioCarro.existsById(id)) {
            repositorioCarro.deleteById(id);
            return ("Carro do ID "+ id + " excluido com sucesso");
        }else {
            return null;
        }
    }

    public List<CarroResponseDTO> listarTodos() {
        return converteToDTOList(repositorioCarro.findAll());
    }

    public List<CarroResponseDTO> buscarPorMarca(String marca) {
         return converteToDTOList(repositorioCarro.findByMarca(marca));
    }

    public List<CarroResponseDTO> buscarPorMarcaModelo(String marca, String modelo) {
        return converteToDTOList(repositorioCarro.findByMarcaAndModeloContainsOrderByPrecoAsc(marca, modelo));
    }

    public void cadastrar(CarroCadastrarDTO dto) {
        if (dto != null){
            repositorioCarro.save(new Carro(dto));
        }else {
            throw new IllegalArgumentException("Preencha todas as informações necessarias");
        }

    }
}
