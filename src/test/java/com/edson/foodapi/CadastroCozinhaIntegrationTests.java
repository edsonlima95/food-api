package com.edson.foodapi;

import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.service.CozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CozinhaService cozinhaService;

    @Test
    public void testaCadastroDeCozinhaChinesa() {

        //Cenario
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("chinesa");

        //ação
        Cozinha novaCozinha = this.cozinhaService.buscarPorId(2L);

        //validação
        assertThat(novaCozinha).isNotNull();

    }


}
