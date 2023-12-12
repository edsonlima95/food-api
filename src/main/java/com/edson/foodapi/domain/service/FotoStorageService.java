package com.edson.foodapi.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    InputStream recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default String gerarNomeArquivoUUID(String nomeArquivo) {
        return UUID.randomUUID().toString() + "-" + nomeArquivo;
    }

    @Builder
    @Getter
    class NovaFoto{

        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;

    }

}
