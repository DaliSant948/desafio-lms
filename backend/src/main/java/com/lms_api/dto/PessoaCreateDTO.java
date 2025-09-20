package com.lms_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String primeiroNome;

    @NotBlank
    @Size(min = 2, max = 100)
    private String ultimoNome;

    @NotNull
    private LocalDate dataNascimento;

    @Email
    @NotBlank
    private String email;

    @Size(max = 50)
    private String telefone;

    @NotBlank
    private String senha;
}
