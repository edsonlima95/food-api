package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.FotoProdutoException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.infra.service.LocalStorageService;
import com.edson.foodapi.domain.infra.service.S3StorageService;
import com.edson.foodapi.domain.model.FotoProduto;
import com.edson.foodapi.domain.repository.FotoProdutoRepository;
import com.edson.foodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class FotoProdutoService {

    @Autowired
    private FotoProdutoRepository fotoProdutoRepository;

    @Autowired
    private LocalStorageService localStorageService;

    @Autowired
    private S3StorageService s3StorageService;

    @Transactional
    public void atualizar(FotoProduto fotoProduto, InputStream inputStream) {

        Long restauranteId = fotoProduto.getRestauranteId();
        Long produtoId = fotoProduto.getProduto().getId();

        //Busca por uma foto existente.
        Optional<FotoProduto> fotoProdutoAtual =
                this.fotoProdutoRepository.findFotoById(restauranteId, produtoId);

        if (fotoProdutoAtual.isPresent()) {
            //Remove os dados da foto que já existe no banco.
            this.fotoProdutoRepository.delete(fotoProdutoAtual.get());

            //Remove a foto do disco
            this.localStorageService.remover(fotoProdutoAtual.get().getNomeArquivo());

        }

        //Seta o nome do arquivo
        String nomeArquivoUUID = this.localStorageService.gerarNomeArquivoUUID(fotoProduto.getNomeArquivo());
        fotoProduto.setNomeArquivo(nomeArquivoUUID);

        //Salva a nova foto no banco.
        this.fotoProdutoRepository.save(fotoProduto);

        //Monta as propriedades do arquivo
        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto
                .builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(inputStream)
                .contentType(fotoProduto.getContentType())
                .build();

        //Salva na pasta local.
       // this.localStorageService.armazenar(novaFoto);

        //Salva na S3 da amazon
        this.s3StorageService.armazenar(novaFoto);
    }

    public FotoProduto buscarPorResutauranteEProdutoId(Long restauranteId, Long produtoId){

        return this.fotoProdutoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoException("A Foto buscada não pode ser encontrada"));
    }

    public void deletar(FotoProduto fotoProduto){
        this.fotoProdutoRepository.delete(fotoProduto);
    }

}
