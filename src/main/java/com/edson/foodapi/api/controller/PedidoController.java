package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.PedidoDTOAssembler;
import com.edson.foodapi.api.core.data.PagebleTranslator;
import com.edson.foodapi.api.model.dto.PedidoDTO;
import com.edson.foodapi.api.model.dto.PedidoResumoDTO;
import com.edson.foodapi.domain.infra.service.SmtpEmailServiceImpl;
import com.edson.foodapi.domain.infra.specs.PedidoSpecs;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.repository.PedidoRepository;
import com.edson.foodapi.domain.filter.PedidoFilter;
import com.edson.foodapi.domain.service.PedidoService;
import com.edson.foodapi.domain.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    //Adiciona filtros dos campos
  /*  @GetMapping
    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {

        List<Pedido> pedidos = this.pedidoService.listar();

        List<PedidoResumoDTO> listResumoDto = this.pedidoDTOAssembler.toListResumoDto(pedidos);

        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(listResumoDto);

        SimpleFilterProvider pedidoFiltrado = new SimpleFilterProvider();

        pedidoFiltrado.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());

        if(StringUtils.isNotBlank(campos)){
            pedidoFiltrado.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        pedidosWrapper.setFilters(pedidoFiltrado);

        return pedidosWrapper;
    }*/

    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter filtros, Pageable pageable) {

        pageable = pageableTradutor(pageable);

        Page<Pedido> pedidosFiltradosPaginados = this.pedidoRepository.findAll(
                PedidoSpecs.comFiltros(filtros), pageable);

        List<PedidoResumoDTO> listResumoDto =
                this.pedidoDTOAssembler.toListResumoDto(pedidosFiltradosPaginados.getContent());

        return new PageImpl<>(listResumoDto, pageable, pedidosFiltradosPaginados.getTotalElements());

    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable Long id) {
        return this.pedidoDTOAssembler.toDto(this.pedidoService.buscarPorId(id));
    }

    @PutMapping("/{id}/confirmar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long id) {
        this.pedidoService.confirmar(id);
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

    /**
     * Conver os campos que vem da requisição, para campos que o sort do pageble entenda.
     * ex: nomeCliente -> cliente.nome
     * ex2: nomeRestaurante -> restaurante.nome
     */
    private Pageable pageableTradutor(Pageable pageable) {

        var mapeamento = Map.of(
                "id", "id",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valotTotal"
        );

        return PagebleTranslator.translate(pageable, mapeamento);
    }

}
