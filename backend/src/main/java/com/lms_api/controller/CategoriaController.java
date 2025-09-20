package com.lms_api.controller;

import com.lms_api.dto.CategoriaCreateDTO;
import com.lms_api.dto.CategoriaDTO;
import com.lms_api.entity.Categoria;
import com.lms_api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gerenciamento de categorias de cursos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas")
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<CategoriaDTO> dtos = categoriaService.listarCategorias()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria pelo seu ID")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Short id) {
        return categoriaService.buscarPorId(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria")
    public ResponseEntity<CategoriaDTO> criar(@RequestBody @Valid CategoriaCreateDTO dto) {
        Categoria categoria = toEntity(dto);
        Categoria salvo = categoriaService.salvar(categoria);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Short id,
                                                  @RequestBody @Valid CategoriaCreateDTO dto) {
        if (categoriaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoria = toEntity(dto);
        categoria.setId(id);
        Categoria atualizado = categoriaService.salvar(categoria);
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar categoria", description = "Remove uma categoria pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Short id) {
        if (categoriaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getCodigo(), categoria.getDescricao());
    }

    private Categoria toEntity(CategoriaCreateDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setCodigo(dto.getCodigo());
        categoria.setDescricao(dto.getDescricao());
        return categoria;
    }
}
