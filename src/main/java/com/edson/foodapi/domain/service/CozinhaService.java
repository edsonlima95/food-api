package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.repository.CozinhaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRespository cozinhaRespository;

    public List<Cozinha> listar() {
        return this.cozinhaRespository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        return this.cozinhaRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recurso não encontrado!!"));
    }

    public void cadastrar(Cozinha cozinha) {
        this.cozinhaRespository.save(cozinha);
    }

    public Cozinha atualizar(Cozinha cozinha) {
        return this.cozinhaRespository.save(cozinha);
    }

    public void deletar(Long id) {

        this.buscarPorId(id);

        this.cozinhaRespository.deleteById(id);
    }

    public List<Cozinha> buscarPorNome(String nome){
        return this.cozinhaRespository.findByNome(nome);
    }
}
