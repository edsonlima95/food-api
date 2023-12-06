package com.edson.foodapi.domain.filter;


import com.edson.foodapi.api.model.dto.RestauranteResumoDTO;
import com.edson.foodapi.api.model.dto.UsuarioDTO;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.model.StatusPedido;
import com.edson.foodapi.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

/**
 * Esta classe será usada como parâmetros para ser filtrados por a classe de specs
 * PedidoSpecs.
 */

@Getter
@Setter
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    //iso = DateTimeFormat.ISO.DATE_TIME força a aceitar a data passada como string.

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicial;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFinal;

}
