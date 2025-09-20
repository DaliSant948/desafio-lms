package com.lms_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaCreateDTO {
    private Long matriculaId;
    private String descricao;
    private int duracaoMinutos;
}
