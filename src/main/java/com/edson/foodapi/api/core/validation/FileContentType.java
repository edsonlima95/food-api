package com.edson.foodapi.api.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileContentTypeValidator.class})
public @interface FileContentType {

        String message() default "somente arquivos do tipo JPG, JPEG e PNG são permitidos";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String[] allowed();

}
