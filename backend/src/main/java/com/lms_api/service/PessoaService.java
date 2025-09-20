package com.lms_api.service;

import com.lms_api.enums.PerfilPessoa;
import com.lms_api.entity.Pessoa;
import com.lms_api.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Pessoa registrarEstudante(Pessoa pessoa) {
        validarIdade(pessoa);
        validarEmailUnico(pessoa.getEmail());

        pessoa.setSenhaHash(passwordEncoder.encode(pessoa.getSenhaHash()));

        pessoa.setPerfil(PerfilPessoa.ESTUDANTE);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa registrarAdministrador(Pessoa pessoa) {
        validarEmailUnico(pessoa.getEmail());

        pessoa.setSenhaHash(passwordEncoder.encode(pessoa.getSenhaHash()));
        pessoa.setPerfil(PerfilPessoa.ADMINISTRADOR);

        return pessoaRepository.save(pessoa);
    }

    private void validarIdade(Pessoa pessoa) {
        int idade = Period.between(pessoa.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 16) {
            throw new IllegalArgumentException("Estudante deve ter pelo menos 16 anos.");
        }
    }

    private void validarEmailUnico(String email) {
        if (pessoaRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
    }

    public List<Pessoa> listarEstudantes() {
        return pessoaRepository.findAll();
    }
}
