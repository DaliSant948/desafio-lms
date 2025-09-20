package com.lms_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaDTO {

    private Long id;
    private Long matriculaId;
    private String descricao;
    private int duracaoMinutos;
    private OffsetDateTime dataExecucao;
}
