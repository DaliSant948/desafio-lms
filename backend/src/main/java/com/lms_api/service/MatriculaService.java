package com.lms_api.service;

import com.lms_api.entity.Curso;
import com.lms_api.entity.Matricula;
import com.lms_api.entity.Pessoa;
import com.lms_api.enums.StatusMatricula;
import com.lms_api.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public Matricula criarMatricula(Pessoa pessoa, Curso curso) {
        validarLimiteCursosAtivos(pessoa);
        validarMatriculaDuplicada(pessoa, curso);

        Matricula matricula = Matricula.builder()
                .pessoa(pessoa)
                .curso(curso)
                .status(StatusMatricula.ATIVO)
                .dataMatricula(OffsetDateTime.now())
                .build();

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarPorPessoa(Pessoa pessoa) {
        return matriculaRepository.findByPessoa(pessoa);
    }

    public Matricula atualizarStatus(Matricula matricula, StatusMatricula novoStatus) {
        matricula.setStatus(novoStatus);
        return matriculaRepository.save(matricula);
    }

    private void validarLimiteCursosAtivos(Pessoa pessoa) {
        long cursosAtivos = matriculaRepository.countByPessoaAndStatus(pessoa, StatusMatricula.ATIVO.name());
        if (cursosAtivos >= 3) {
            throw new IllegalArgumentException("Não é possível se matricular em mais de 3 cursos ativos.");
        }
    }

    private void validarMatriculaDuplicada(Pessoa pessoa, Curso curso) {
        if (matriculaRepository.existsByPessoaAndCurso(pessoa, curso)) {
            throw new IllegalArgumentException("Pessoa já está matriculada nesse curso.");
        }
    }
}
