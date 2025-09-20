package com.lms_api.service;

import com.lms_api.entity.Curso;
import com.lms_api.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso criarCurso(Curso curso) {
        validarNomeUnico(curso.getNome());
        validarDuracaoMaxima(curso);

        curso.setAtivo(true);

        return cursoRepository.save(curso);
    }

    public Curso atualizarCurso(Curso curso) {
        Curso existente = cursoRepository.findById(curso.getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        if (!existente.getNome().equals(curso.getNome())) {
            validarNomeUnico(curso.getNome());
        }

        validarDuracaoMaxima(curso);

        existente.setNome(curso.getNome());
        existente.setDescricao(curso.getDescricao());
        existente.setDataInicio(curso.getDataInicio());
        existente.setDataFim(curso.getDataFim());
        existente.setAtivo(curso.getAtivo());

        return cursoRepository.save(existente);
    }

    public List<Curso> listarCursosAtivos() {
        return cursoRepository.findByAtivoTrue();
    }

    public void deletarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    private void validarNomeUnico(String nome) {
        if (cursoRepository.existsByNome(nome)) {
            throw new IllegalArgumentException("Curso com esse nome já existe.");
        }
    }

    private void validarDuracaoMaxima(Curso curso) {
        long meses = ChronoUnit.MONTHS.between(curso.getDataInicio(), curso.getDataFim());
        if (meses > 6) {
            throw new IllegalArgumentException("Curso não pode ter mais de 6 meses de duração.");
        }
    }
}
