package com.lms_api.repository;

import com.lms_api.entity.Tarefa;
import com.lms_api.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByMatricula(Matricula matricula);
}
