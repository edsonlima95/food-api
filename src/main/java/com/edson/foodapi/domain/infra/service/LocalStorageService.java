package com.edson.foodapi.domain.infra.service;


import com.edson.foodapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalStorageService implements FotoStorageService {

    @Value("${foodapi.storage.local}")
    private String caminhoArquivo;

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path novoCaminho = Path.of(caminhoArquivo,novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(novoCaminho));

        } catch (Exception e) {
            throw new StorageException("Falha ao tentar armazenar o arquivo",e);
        }
    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path novoCaminho = Path.of(caminhoArquivo, nomeArquivo);

           return Files.newInputStream(novoCaminho);

        } catch (Exception e) {
            throw new StorageException("Falha ao tentar retornar um arquivo",e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {

        try {

            Path novoCaminho = Path.of(caminhoArquivo,nomeArquivo);
            Files.deleteIfExists(novoCaminho);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar remover o arquivo local");
        }
    }
}
