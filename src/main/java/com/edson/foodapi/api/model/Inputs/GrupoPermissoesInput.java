package com.edson.foodapi.api.model.Inputs;


import com.edson.foodapi.api.model.dto.PermissoesDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GrupoPermissoesInput {

    private Set<PermissoesDTO> permissoes = new HashSet<>();

}
