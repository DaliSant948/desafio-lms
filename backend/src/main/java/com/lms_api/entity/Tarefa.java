package com.lms_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id", nullable = false)
    @NotNull
    private Matricula matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull
    private Categoria categoria;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    @Column(name = "data_execucao", nullable = false)
    private OffsetDateTime dataExecucao;

    @NotNull
    @Positive
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    @PrePersist
    public void prePersist() {
        this.dataExecucao = OffsetDateTime.now();
    }
}
