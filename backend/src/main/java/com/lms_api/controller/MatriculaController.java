package com.lms_api.controller;

import com.lms_api.dto.MatriculaCreateDTO;
import com.lms_api.dto.MatriculaDTO;
import com.lms_api.entity.Curso;
import com.lms_api.entity.Matricula;
import com.lms_api.entity.Pessoa;
import com.lms_api.repository.CursoRepository;
import com.lms_api.repository.PessoaRepository;
import com.lms_api.service.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matriculas")
@Tag(name = "Matrículas", description = "Operações de matrícula dos estudantes")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;

    @PostMapping
    @Operation(summary = "Criar matrícula", description = "Registra uma matrícula de estudante em um curso")
    public ResponseEntity<MatriculaDTO> criarMatricula(@RequestBody @Valid MatriculaCreateDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Matricula matricula = matriculaService.criarMatricula(pessoa, curso);
        return ResponseEntity.ok(toDTO(matricula));
    }

    @GetMapping("/pessoa/{pessoaId}")
    @Operation(summary = "Listar matrículas por pessoa", description = "Retorna todas as matrículas de um estudante")
    public ResponseEntity<List<MatriculaDTO>> listarPorPessoa(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        List<MatriculaDTO> dtos = matriculaService.listarPorPessoa(pessoa)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private MatriculaDTO toDTO(Matricula matricula) {
        return MatriculaDTO.builder()
                .id(matricula.getId())
                .pessoaId(matricula.getPessoa().getId())
                .cursoId(matricula.getCurso().getId())
                .status(matricula.getStatus())
                .dataMatricula(matricula.getDataMatricula())
                .build();
    }
}
