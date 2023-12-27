package com.edson.foodapi.api.controller.openapidoc;


import com.edson.foodapi.domain.model.Cozinha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Cozinha")
public interface CozinhaControllerOpenApiDoc {

    @Operation(summary = "Lista as cozinhas")
    public List<Cozinha> listar();

    @Operation(summary = "Cadastra uma cozinha")
    public void cadastrar(
            @Parameter(description = "Representação de uma cozinha") Cozinha cozinha);

    @Operation(summary = "Busca uma cozinha por ID")
    public Cozinha buscarPorId(@Parameter(description = "ID de uma cozinha") Long id);


    @Operation(summary = "Atualiza uma cozinha")
    public Cozinha atualizar(@Parameter(description = "ID de uma cozinha") Cozinha cozinha, Long id);


    @Operation(summary = "Exclui uma cozinha")
    public void deletar(@Parameter(description = "ID de uma cozinha") Long id);

    @Operation(summary = "Lista as cozinhas por nome")
    public ResponseEntity<List<Cozinha>> buscarPorNome(@Parameter(description = "Nome de uma cozinha") String nome);
}
