package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void cadastrar(Restaurante restaurante) {

        this.cidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());

        this.cozinhaService.buscarPorId(restaurante.getCozinha().getId());

        this.restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante) {

        this.cidadeService.buscarPorId(restaurante.getEndereco().getCidade().getId());

        this.cozinhaService.buscarPorId(restaurante.getCozinha().getId());

        return this.restauranteRepository.save(restaurante);
    }

    public void deletar(Long id) {

        this.buscarPorId(id);

        this.restauranteRepository.deleteById(id);
    }

    public void ativar(Long restauranteId){

        Restaurante restaurante = this.restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new NotFoundException(restauranteId));

        restaurante.setAtivo(true);

        this.restauranteRepository.save(restaurante);

    }

    public void inativar(Long restauranteId){

        Restaurante restaurante = this.restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new NotFoundException("Recurso não encontrado!"));

        restaurante.setAtivo(false);

        this.restauranteRepository.save(restaurante);

    }

    public void abrir(Long restauranteId){

        Restaurante restaurante = this.restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new NotFoundException(restauranteId));

        restaurante.setAberto(true);

        this.restauranteRepository.save(restaurante);

    }

    public void fechar(Long restauranteId){

        Restaurante restaurante = this.restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new NotFoundException(restauranteId));

        restaurante.setAberto(false);

        this.restauranteRepository.save(restaurante);

    }

    public void fechamentoEmMassa(Set<Long> restaurantesId){
        restaurantesId.forEach(this::fechar);
    }

    public void aberturaEmMassa(Set<Long> restaurantesId){
        restaurantesId.forEach(this::abrir);
    }


    //METODOS DAS IMPLEMENTAÇÕES DO REPOSITORIO CRITERIA.
   /* public List<Restaurante> buscaCompleta(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return this.restauranteRepository.buscarCompleta(nome, taxaInicial, taxaFinal);
    }*/

   /* public List<Restaurante> buscaComFreteGratis(String nome) {
        return this.restauranteRepository.comFreteGratisENome(nome);
    }*/

    public Optional<Restaurante> buscaPrimeiro() {
        return this.restauranteRepository.buscaPrimeiro();
    }

}
