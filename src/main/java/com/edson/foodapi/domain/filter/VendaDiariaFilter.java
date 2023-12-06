package com.edson.foodapi.domain.filter;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
@Getter
@Setter
public class VendaDiariaFilter {

    private Long restauranteId;

    //iso = DateTimeFormat.ISO.DATE_TIME força a aceitar a data passada como string.
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicial;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFinal;

}
