package com.example.VEND.controller;

import com.example.VEND.dto.CarroDTO;
import com.example.VEND.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<CarroDTO> listarCarros() {
        return carroService.listarTodos();
    }

    @GetMapping("/{id}")
    public CarroDTO buscarPorId(@PathVariable Long id) {
        return carroService.buscarPorId(id);
    }

    @GetMapping("/marca/{marca}")
    public List<CarroDTO> buscarCarrosPorMarca(@PathVariable String marca) {
        return carroService.buscarPorMarca(marca);
    }

    @GetMapping("/marca/{marca}/modelo/{modelo}")
    public List<CarroDTO> buscarCarrosPorMarcaModelo(@PathVariable String marca, @PathVariable String modelo) {
        return carroService.buscarPorMarcaModelo(marca, modelo);
    }

    @DeleteMapping("/{id}")
    public String deletarCarro(@PathVariable Long id) {
        return carroService.deletarCarro(id);
    }
}
