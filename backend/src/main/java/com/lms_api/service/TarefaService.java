package com.lms_api.service;

import com.lms_api.entity.Matricula;
import com.lms_api.entity.Tarefa;
import com.lms_api.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public Tarefa logarTarefa(Matricula matricula, String descricao, int duracaoMinutos) {
        validarDuracao(duracaoMinutos);

        Tarefa tarefa = Tarefa.builder()
                .matricula(matricula)
                .descricao(descricao)
                .duracaoMinutos(duracaoMinutos)
                .dataExecucao(OffsetDateTime.now())
                .build();

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarPorMatricula(Matricula matricula) {
        return tarefaRepository.findByMatricula(matricula);
    }
    
    private void validarDuracao(int duracaoMinutos) {
        if (duracaoMinutos < 30) {
            throw new IllegalArgumentException("A duração mínima de uma tarefa é 30 minutos.");
        }
    }
}
