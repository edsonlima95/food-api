package com.edson.foodapi.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)//Não retorna propriedades null.
@Getter
@Builder
public class Problem {

    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String userMessage;
    private LocalDateTime dateTime;
    private List<FieldValidation> fieldValidations;

    @Getter
    @AllArgsConstructor
    static class FieldValidation {

        private String field;
        private String message;

    }

}
