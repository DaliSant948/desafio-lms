package com.lms_api.dto;

import com.lms_api.entity.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public Pessoa toEntity(BCryptPasswordEncoder passwordEncoder) {
        return Pessoa.builder()
                .primeiroNome(primeiroNome)
                .ultimoNome(ultimoNome)
                .dataNascimento(dataNascimento)
                .email(email)
                .telefone(telefone)
                .senhaHash(passwordEncoder.encode(senha))
                .build();
    }
}
