package com.lms_api.dto;

import com.lms_api.enums.StatusMatricula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaDTO {

    private Long id;
    private Long pessoaId;
    private Long cursoId;
    private StatusMatricula status;
    private OffsetDateTime dataMatricula;
}
