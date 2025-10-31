package com.example.VEND.service;

import com.example.VEND.dto.CarroDTO;
import com.example.VEND.dto.CarroRequestDTO;
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

    private CarroDTO converteToDTO(Carro carro) {
        return new CarroDTO(carro.getId(), carro.getModelo(), carro.getMarca(), carro.getAno(), carro.getCarroceria(), carro.getPreco(), carro.getUsuarioCliente());
    }

    private List<CarroDTO> converteToDTOList(List<Carro> carros) {
        return carros.stream()
                .map(c -> new CarroDTO(c.getId(), c.getModelo(), c.getMarca(), c.getAno(), c.getCarroceria(), c.getPreco(), c.getUsuarioCliente()))
                .collect(Collectors.toList());
    }

    public CarroDTO buscarPorId(Long id) {
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

    public List<CarroDTO> listarTodos() {
        return converteToDTOList(repositorioCarro.findAll());
    }

    public List<CarroDTO> buscarPorMarca(String marca) {
         return converteToDTOList(repositorioCarro.findByMarca(marca));
    }

    public List<CarroDTO> buscarPorMarcaModelo(String marca, String modelo) {
        return converteToDTOList(repositorioCarro.findByMarcaAndModeloContainsOrderByPrecoAsc(marca, modelo));
    }

    public CarroDTO insertCarro(CarroRequestDTO carroRequestDTO) {
        Carro carro = new Carro();
        carro.setModelo(carroRequestDTO.modelo());
        carro.setMarca(carroRequestDTO.marca());
        carro.setAno(carroRequestDTO.ano());
        carro.setCarroceria(carroRequestDTO.carroceria());
        carro.setPreco(carroRequestDTO.preco());

        return converteToDTO(repositorioCarro.save(carro));
    }
}
