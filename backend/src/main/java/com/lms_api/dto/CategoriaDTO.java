package com.lms_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

    public record CategoriaDTO(

            Short id,

            @NotBlank
            @Size(max = 50)
            String codigo,

            @NotBlank
            @Size(max = 200)
            String descricao
    ) {}
