package com.lms_api.controller;

import com.lms_api.dto.PessoaCreateDTO;
import com.lms_api.dto.PessoaDTO;
import com.lms_api.entity.Pessoa;
import com.lms_api.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas", description = "Operações relacionadas a pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping("/estudante")
    @Operation(summary = "Registrar estudante", description = "Cria um novo estudante no sistema")
    public ResponseEntity<PessoaDTO> registrarEstudante(@RequestBody PessoaCreateDTO dto) {
        Pessoa pessoa = Pessoa.builder()
                .primeiroNome(dto.getPrimeiroNome())
                .ultimoNome(dto.getUltimoNome())
                .dataNascimento(dto.getDataNascimento())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senhaHash(dto.getSenha())
                .build();

        Pessoa criado = pessoaService.registrarEstudante(pessoa);

        return ResponseEntity.ok(toDTO(criado));
    }

    @PostMapping("/administrador")
    @Operation(summary = "Registrar administrador", description = "Cria um novo administrador no sistema")
    public ResponseEntity<PessoaDTO> registrarAdministrador(@RequestBody PessoaCreateDTO dto) {
        Pessoa pessoa = Pessoa.builder()
                .primeiroNome(dto.getPrimeiroNome())
                .ultimoNome(dto.getUltimoNome())
                .dataNascimento(dto.getDataNascimento())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senhaHash(dto.getSenha())
                .build();

        Pessoa criado = pessoaService.registrarAdministrador(pessoa);

        return ResponseEntity.ok(toDTO(criado));
    }

    @GetMapping("/estudantes")
    @Operation(summary = "Listar estudantes", description = "Retorna a lista de todos os estudantes cadastrados")
    public ResponseEntity<List<PessoaDTO>> listarEstudantes() {
        return ResponseEntity.ok(
                pessoaService.listarEstudantes()
                        .stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    private PessoaDTO toDTO(Pessoa pessoa) {
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .primeiroNome(pessoa.getPrimeiroNome())
                .ultimoNome(pessoa.getUltimoNome())
                .dataNascimento(pessoa.getDataNascimento())
                .email(pessoa.getEmail())
                .telefone(pessoa.getTelefone())
                .perfil(pessoa.getPerfil())
                .ultimoLogin(pessoa.getUltimoLogin())
                .build();
    }
}
