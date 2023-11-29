package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.PedidoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.PedidoInput;
import com.edson.foodapi.api.model.dto.PedidoDTO;
import com.edson.foodapi.api.model.dto.PedidoResumoDTO;
import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.model.StatusPedido;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.service.EmissaoPedidoService;
import com.edson.foodapi.domain.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;


    @GetMapping
    public List<PedidoResumoDTO> listar() {
        return this.pedidoDTOAssembler.toListResumoDto(this.pedidoService.listar());
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable Long id) {
        return this.pedidoDTOAssembler.toDto(this.pedidoService.buscarPorId(id));
    }

    @PutMapping("/{id}/confirmar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long id) {

        Pedido pedido = this.pedidoService.buscarPorId(id);

        pedido.confirmar();

        this.pedidoService.atualizar(pedido);
    }

    @PutMapping("/{id}/entregar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable Long id) {

        Pedido pedido = this.pedidoService.buscarPorId(id);

        pedido.entregar();

        this.pedidoService.atualizar(pedido);
    }

    @DeleteMapping("/{id}/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {

        Pedido pedido = this.pedidoService.buscarPorId(id);

        pedido.cancelar();

        this.pedidoService.atualizar(pedido);
    }

}
