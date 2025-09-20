package com.lms_api.controller;

import com.lms_api.dto.CursoCreateDTO;
import com.lms_api.dto.CursoDTO;
import com.lms_api.entity.Curso;
import com.lms_api.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "CRUD e listagem de cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Criar curso", description = "Cria um novo curso no sistema")
    public ResponseEntity<CursoDTO> criarCurso(@RequestBody @Valid CursoCreateDTO dto) {
        Curso curso = toEntity(dto);
        Curso criado = cursoService.criarCurso(curso);
        return ResponseEntity.ok(toDTO(criado));
    }

    @GetMapping("/ativos")
    @Operation(summary = "Listar cursos ativos", description = "Retorna todos os cursos ativos")
    public ResponseEntity<List<CursoDTO>> listarCursosAtivos() {
        List<CursoDTO> cursos = cursoService.listarCursosAtivos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cursos);
    }

    private CursoDTO toDTO(Curso curso) {
        return CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .dataInicio(curso.getDataInicio())
                .dataFim(curso.getDataFim())
                .ativo(curso.getAtivo())
                .build();
    }

    private Curso toEntity(CursoCreateDTO dto) {
        return Curso.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .build();
    }
}
