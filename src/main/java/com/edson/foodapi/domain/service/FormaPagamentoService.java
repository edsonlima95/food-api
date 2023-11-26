package com.edson.foodapi.domain.service;


import com.edson.foodapi.api.model.dto.FormaPagamentoDTO;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.FormaPagamento;
import com.edson.foodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listar() {
        return this.formaPagamentoRepository.findAll();
    }

    public void cadastrar(FormaPagamento formaPagamento) {
        this.formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento buscarPorId(Long id) {
        return this.formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public FormaPagamento atualizar(FormaPagamento formaPagamento) {
        return this.formaPagamentoRepository.save(formaPagamento);
    }

    public void deletar(Long id) {
       this.formaPagamentoRepository.deleteById(id);
    }
}
