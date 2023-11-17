package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return this.restauranteRepository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        return this.restauranteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recurso não encontrado!!"));
    }

    public void cadastrar(Restaurante restaurante) {

        this.cidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());

        this.cozinhaService.buscarPorId(restaurante.getCozinha().getId());

        this.restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante) {

        System.out.println("linha: 48 "+restaurante.getDataCriacao());

        this.cidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());

        this.cozinhaService.buscarPorId(restaurante.getCozinha().getId());

        return this.restauranteRepository.save(restaurante);
    }

    public void deletar(Long id) {

        this.buscarPorId(id);

        this.restauranteRepository.deleteById(id);
    }

    public List<Restaurante> buscaCompleta(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return this.restauranteRepository.buscarCompleta(nome, taxaInicial, taxaFinal);
    }

    public List<Restaurante> buscaComFreteGratis(String nome) {
        return this.restauranteRepository.comFreteGratisENome(nome);
    }

    public Optional<Restaurante> buscaPrimeiro() {
        return this.restauranteRepository.buscaPrimeiro();
    }
}
