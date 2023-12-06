package com.edson.foodapi.domain.service;

import com.edson.foodapi.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVedasDiarias(VendaDiariaFilter filtros);

}
