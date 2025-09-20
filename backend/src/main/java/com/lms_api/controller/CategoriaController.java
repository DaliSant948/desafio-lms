package com.lms_api.controller;

import com.lms_api.dto.CategoriaCreateDTO;
import com.lms_api.dto.CategoriaDTO;
import com.lms_api.entity.Categoria;
import com.lms_api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<CategoriaDTO> dtos = categoriaService.listarCategorias()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Short id) {
        return categoriaService.buscarPorId(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody @Valid CategoriaCreateDTO dto) {
        Categoria categoria = toEntity(dto);
        Categoria salvo = categoriaService.salvar(categoria);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
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
