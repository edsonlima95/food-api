package com.edson.foodapi.domain.service;

import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.model.ProdutoDto;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    public void cadastrar(Produto produto){

        this.restauranteService.buscarPorId(produto.getRestaurante().getId());

        this.produtoRepository.save(produto);
    }

    public List<Produto> buscarPorAtivos(Restaurante restaurante){
        return this.produtoRepository.findAtivosByRestaurante(restaurante);
    }

    public List<Produto> buscarPorRestaurenteId(Restaurante restaurante){
        return this.produtoRepository.findByRestaurante(restaurante);
    }

    public Produto buscarPorRestauranteEProdutoId(Long restauranteId, Long produtoId) {
       return this.produtoRepository.findByRestaurenteId(restauranteId, produtoId)
               .orElseThrow(() ->
                       new NotFoundException(String.format("Produto de codigo %d, não existe para o " +
                               " restaurante de código %d",produtoId, restauranteId)));
    }

}
