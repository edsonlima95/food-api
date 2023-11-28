package com.edson.foodapi.api.controller;

import com.edson.foodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.edson.foodapi.api.model.dto.FormaPagamentoDTO;
import com.edson.foodapi.domain.model.FormaPagamento;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.FormaPagamentoService;
import com.edson.foodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/forma-pagamentos")
public class RestauranteFormaPagamentosController {


    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @GetMapping
    public List<FormaPagamentoDTO> formaPagamentoLista(@PathVariable Long restauranteId) {

        Restaurante restaurante = this.restauranteService.buscarPorId(restauranteId);

        return this.formaPagamentoDTOAssembler.toListDTO(restaurante.getFormaPagamentos());

    }

    @PutMapping("/{formaPagamentoId}")
    public void associarformaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {

        Restaurante restaurante = this.restauranteService.buscarPorId(restauranteId);

        FormaPagamento formaPagamento = this.formaPagamentoService.buscarPorId(formaPagamentoId);

        restaurante.getFormaPagamentos().add(formaPagamento);

        this.restauranteService.atualizar(restaurante);

    }

    @DeleteMapping("/{formaPagamentoId}")
    public void deassociarformaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {

        Restaurante restaurante = this.restauranteService.buscarPorId(restauranteId);

        FormaPagamento formaPagamento = this.formaPagamentoService.buscarPorId(formaPagamentoId);

        restaurante.getFormaPagamentos().remove(formaPagamento);

        this.restauranteService.atualizar(restaurante);


    }

}
