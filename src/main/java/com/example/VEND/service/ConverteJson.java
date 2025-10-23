package com.example.VEND.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteJson {
    ObjectMapper mapper = new ObjectMapper();

    public <T> T getDados(String json, Class<T> classe) {

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desseraliazar o json : " + e.getMessage());
        }
    }

    public <T> List<T> getDadosLista(String json, Class<T> classe) {
        try {
            return mapper.readValue(json,
                    mapper.getTypeFactory().constructCollectionType(List.class, classe));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desserializar o json: " + e.getMessage());
        }
    }

}
