package com.edson.foodapi.api.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PagebleTranslator {

    /*
     * Converte as propriedades do sort, e retorna um novo Pageble
     */
    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {

        var orders = pageable.getSort().stream()
                .filter(field -> fieldsMapping.containsKey(field.getProperty())) //Filtra campos nao nulos.
                .map(order -> new Sort.Order(order.getDirection(), fieldsMapping.get(order.getProperty())))//Converte os campos
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(orders));//Retorna o resultado ordenado
    }

}
