package com.lms_api.repository;

import com.lms_api.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Short> {

    boolean existsByCodigo(String codigo);
}
