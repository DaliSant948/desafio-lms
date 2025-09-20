package com.lms_api.repository;

import com.lms_api.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByEmail(String email);
}
