package com.lms_api.entity;

import com.lms_api.enums.PerfilPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name ="pessoas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "primeiro_nome", nullable = false)
    private String primeiroNome;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "ultimo_nome", nullable = false)
    private String ultimoNome;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Email
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 50)
    @Column(length = 50)
    private String telefone;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PerfilPessoa perfil;

    @Column(name = "ultimo_login")
    private OffsetDateTime ultimoLogin;

    @NotNull
    @Column(name = "criado_em")
    private OffsetDateTime criadoEm;

    @OneToMany(mappedBy = "pessoa")
    private List<Matricula> matriculas;

    @PrePersist
    public void prePersist() {
        this.criadoEm = OffsetDateTime.now();
    }
}
