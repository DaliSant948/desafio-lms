package com.lms_api.controller;

import com.lms_api.config.JwtUtil;
import com.lms_api.dto.PessoaCreateDTO;
import com.lms_api.entity.Pessoa;
import com.lms_api.service.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Login e registro de usuários")
@RequiredArgsConstructor
public class AuthController {

    private final PessoaService pessoaService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PessoaCreateDTO dto) {
        Pessoa pessoa = pessoaService.buscarPorEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getSenha(), pessoa.getSenhaHash())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        String token = jwtUtil.gerarToken(pessoa.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PessoaCreateDTO dto) {
        Pessoa pessoa = pessoaService.registrarEstudante(dto.toEntity(passwordEncoder));
        String token = jwtUtil.gerarToken(pessoa.getEmail());
        return ResponseEntity.ok(token);
    }
}
