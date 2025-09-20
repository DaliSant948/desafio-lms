package com.lms_api.controller;

import com.lms_api.dto.TarefaCreateDTO;
import com.lms_api.dto.TarefaDTO;
import com.lms_api.entity.Matricula;
import com.lms_api.entity.Tarefa;
import com.lms_api.repository.MatriculaRepository;
import com.lms_api.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
@Tag(name = "Tarefas", description = "Registros de tarefas de estudantes")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;
    private final MatriculaRepository matriculaRepository;

    @PostMapping
    @Operation(summary = "Registrar tarefa", description = "Registra uma tarefa realizada por um estudante")
    public ResponseEntity<TarefaDTO> logarTarefa(@RequestBody @Valid TarefaCreateDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.getMatriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        Tarefa tarefa = tarefaService.logarTarefa(matricula, dto.getDescricao(), dto.getDuracaoMinutos());
        return ResponseEntity.ok(toDTO(tarefa));
    }

    @GetMapping("/matricula/{matriculaId}")
    @Operation(summary = "Listar tarefas por matrícula", description = "Lista todas as tarefas de uma matrícula")
    public ResponseEntity<List<TarefaDTO>> listarPorMatricula(@PathVariable Long matriculaId) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        List<TarefaDTO> dtos = tarefaService.listarPorMatricula(matricula)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private TarefaDTO toDTO(Tarefa tarefa) {
        return TarefaDTO.builder()
                .id(tarefa.getId())
                .matriculaId(tarefa.getMatricula().getId())
                .descricao(tarefa.getDescricao())
                .duracaoMinutos(tarefa.getDuracaoMinutos())
                .dataExecucao(tarefa.getDataExecucao())
                .build();
    }
}
