package com.lms_api.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoCreateDTO {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
