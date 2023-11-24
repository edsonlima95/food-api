package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.FormaPagamentoInput;
import com.edson.foodapi.api.model.dto.FormaPagamentoDTO;
import com.edson.foodapi.domain.model.FormaPagamento;
import com.edson.foodapi.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forma-pagamentos")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {

        List<FormaPagamento> formaPagamentoList = this.formaPagamentoService.listar();

        return this.formaPagamentoDTOAssembler.toListDTO(formaPagamentoList);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cadastrar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {

        FormaPagamento formaPagamento = formaPagamentoDTOAssembler.toModel(formaPagamentoInput);

        this.formaPagamentoService.cadastrar(formaPagamento);
    }

    @PutMapping("/{id}")
    public FormaPagamentoDTO atualizar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput, @PathVariable Long id) {

        FormaPagamento formaPagamentoAtual = this.formaPagamentoService.buscarPorId(id);

        formaPagamentoDTOAssembler.toDomainObject(formaPagamentoInput, formaPagamentoAtual);

        FormaPagamento formaPagamento = this.formaPagamentoService.atualizar(formaPagamentoAtual);

        return this.formaPagamentoDTOAssembler.toDTO(formaPagamento);
    }

    @GetMapping("/{id}")
    public FormaPagamentoDTO buscarPorId(@PathVariable Long id) {

        FormaPagamento formaPagamento = this.formaPagamentoService.buscarPorId(id);

        return this.formaPagamentoDTOAssembler.toDTO(formaPagamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        this.formaPagamentoService.buscarPorId(id);

        this.formaPagamentoService.deletar(id);
    }

}
