package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.PedidoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.PedidoInput;
import com.edson.foodapi.api.model.dto.PedidoDTO;
import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.service.EmissaoPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoDTOAssembler.toModel(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(2L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoDTOAssembler.toDto(novoPedido);
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }


}
