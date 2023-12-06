package com.edson.foodapi.domain.service;

import com.edson.foodapi.domain.filter.VendaDiariaFilter;
import com.edson.foodapi.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiaria(VendaDiariaFilter filtros);

}
