package com.edson.foodapi.api.model.dto;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
public class TokenDTO {

    private String usuario;
    private Boolean autenticado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private String token;
    private String refreshToken;

}
