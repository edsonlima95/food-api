package com.edson.foodapi.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface SendEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {

        private String corpo;
        private String assunto;
        @Singular
        private Set<String> destinatarios;
        @Singular("variavel")
        private Map<String, Object> variaveis;
    }

}
