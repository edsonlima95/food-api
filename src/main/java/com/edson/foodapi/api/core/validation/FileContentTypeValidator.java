package com.edson.foodapi.api.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private final String[] contentType = {"image/jpeg", "image/png"};
    private String[] types;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.types = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        List<String> list = Arrays.asList(this.types);

        return list.contains(value.getContentType());
    }
}
