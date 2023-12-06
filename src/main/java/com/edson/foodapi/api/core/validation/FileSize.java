package com.edson.foodapi.api.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {

        String message() default "tamanho do arquivo é maior do que o permitido";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String max();

}
