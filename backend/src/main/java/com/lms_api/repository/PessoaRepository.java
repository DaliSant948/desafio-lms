package com.lms_api.repository;

import com.lms_api.entity.Pessoa;
import com.lms_api.enums.PerfilPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByEmail(String email);

    Optional<Pessoa> findByEmail(String email);

    List<Pessoa> findByPerfil(PerfilPessoa perfil);
}
