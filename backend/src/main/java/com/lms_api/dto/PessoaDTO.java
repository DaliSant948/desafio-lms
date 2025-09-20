package com.lms_api.dto;

import com.lms_api.enums.PerfilPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Long id;
    private String primeiroNome;
    private String ultimoNome;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private PerfilPessoa perfil;
    private OffsetDateTime ultimoLogin;
}
