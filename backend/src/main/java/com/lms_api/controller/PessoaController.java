package com.lms_api.controller;

import com.lms_api.dto.PessoaCreateDTO;
import com.lms_api.dto.PessoaDTO;
import com.lms_api.entity.Pessoa;
import com.lms_api.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping("/estudante")
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
