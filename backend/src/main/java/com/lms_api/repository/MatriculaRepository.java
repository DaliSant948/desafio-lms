package com.lms_api.repository;

import com.lms_api.entity.Matricula;
import com.lms_api.entity.Pessoa;
import com.lms_api.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByPessoaAndCurso(Pessoa pessoa, Curso curso);

    List<Matricula> findByPessoa(Pessoa pessoa);

    long countByPessoaAndStatus(Pessoa pessoa, String status);

}
