package com.lms_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "categorias", uniqueConstraints = {@UniqueConstraint(columnNames = "codigo")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    private Short id;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotNull
    @Size(max = 200)
    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

    @NotNull
    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        this.criadoEm = OffsetDateTime.now();
    }
}
