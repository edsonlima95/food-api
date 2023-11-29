package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar(){
        return this.pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id){
        return this.pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Pedido atualizar(Pedido pedido){
      return  this.pedidoRepository.save(pedido);
    }
}
