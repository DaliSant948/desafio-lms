package com.lms_api.repository;

import com.lms_api.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByNome(String nome);

    List<Curso> findByAtivoTrue();
}
