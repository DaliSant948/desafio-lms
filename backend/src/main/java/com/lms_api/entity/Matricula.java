package com.lms_api.entity;

import com.lms_api.enums.StatusMatricula;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "matriculas", uniqueConstraints = {@UniqueConstraint(columnNames = {"pessoa_id", "curso_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "data_matricula", nullable = false, updatable = false)
    private OffsetDateTime dataMatricula;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private StatusMatricula status;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

    @PrePersist
    public void prePersist() {
        this.dataMatricula = OffsetDateTime.now();
    }
}
