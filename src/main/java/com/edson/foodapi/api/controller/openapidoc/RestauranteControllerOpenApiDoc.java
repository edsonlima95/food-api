package com.edson.foodapi.api.controller.openapidoc;


import com.edson.foodapi.api.assembler.RestauranteDTOAssembler;
import com.edson.foodapi.api.model.Inputs.RestauranteInput;
import com.edson.foodapi.api.model.dto.RestauranteDTO;
import com.edson.foodapi.api.model.view.RestauranteView;
import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.repository.RestauranteRepository;
import com.edson.foodapi.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Tag(name = "Restaurante")
public interface RestauranteControllerOpenApiDoc {


    @Operation(summary = "Lista todos os restaurantes")
    public Page<RestauranteDTO> listar(@Parameter(description = "Define parametros para paginação") Pageable pageable);


    public List<RestauranteDTO> listarApenasNomes();

    public void cadastrar(RestauranteInput restauranteInput);

    public RestauranteDTO buscarPorId(@PathVariable Long id);

    public RestauranteDTO atualizar(RestauranteInput restauranteInput, Long id);


    public void deletar(Long id);


    public Optional<Restaurante> buscaPrimeiro();


    public void ativar(Long id);


    public void inativar(Long id);


    public void abrir(Long id);

    public void fechar(Long id);

    public void fechamentoCompleto(Set<Long> restaurantesId);


    public void aberturaCompleto(Set<Long> restaurantesId);
}
