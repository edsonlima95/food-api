package com.edson.foodapi.api.core;

import com.edson.foodapi.api.model.mixin.RestauranteMixin;
import com.edson.foodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/**
 * Classe que configura os modelos, assim evitando muitas anotações no modelo.
 */

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
