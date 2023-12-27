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
    private FotoStorageService fotoService;

    @Transactional
    public void atualizar(FotoProduto fotoProduto, InputStream inputStream) {

        Long restauranteId = fotoProduto.getRestauranteId();
        Long produtoId = fotoProduto.getProduto().getId();

        //Busca por uma foto existente.
        Optional<FotoProduto> fotoProdutoAtual =
                this.fotoProdutoRepository.findFotoById(restauranteId, produtoId);

        String nomeArquivoAntigo = null;

        if (fotoProdutoAtual.isPresent()) {
            //Remove os dados da foto que já existe no banco.
            this.fotoProdutoRepository.delete(fotoProdutoAtual.get());

            //Remove a foto do disco
            nomeArquivoAntigo = fotoProdutoAtual.get().getNomeArquivo();
        }

        //Seta o nome do arquivo
        String nomeArquivoUUID = this.fotoService.gerarNomeArquivoUUID(fotoProduto.getNomeArquivo());
        fotoProduto.setNomeArquivo(nomeArquivoUUID);

        //Salva a nova foto no banco.
        fotoProduto = this.fotoProdutoRepository.save(fotoProduto);

        //Monta as propriedades do arquivo
        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto
                .builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(inputStream)
                .contentType(fotoProduto.getContentType())
                .build();

        System.out.println(nomeArquivoAntigo);
        this.fotoService.enviarArquivo(nomeArquivoAntigo, novaFoto);
    }

    public FotoProduto buscarPorResutauranteEProdutoId(Long restauranteId, Long produtoId){

        return this.fotoProdutoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoException("A Foto buscada não pode ser encontrada"));
    }

    public void deletar(FotoProduto fotoProduto){
        this.fotoProdutoRepository.delete(fotoProduto);
    }

}
