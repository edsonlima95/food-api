package com.edson.foodapi.api.core.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * Esta classe costumiza o retorno de uma objeto, com as propriedades
 * ou seja monta um objeto de retorno personalizado.
 */

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator generator,
                          SerializerProvider serializerProvider) throws IOException {

        generator.writeStartObject();

        //Define as propriedades serializadas.
        generator.writeObjectField("content", page.getContent());
        generator.writeObjectField("size", page.getSize());
        generator.writeObjectField("totalElements", page.getTotalElements());
        generator.writeObjectField("totalPage", page.getTotalPages());
        generator.writeObjectField("number", page.getNumber());

        generator.writeEndObject();

    }
}
