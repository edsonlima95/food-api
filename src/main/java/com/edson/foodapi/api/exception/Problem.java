package com.edson.foodapi.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)//Não retorna propriedades null.
@Getter
@Builder
public class Problem {

    private String type;
    private String title;
    private Integer status;
    private String detail;

}
