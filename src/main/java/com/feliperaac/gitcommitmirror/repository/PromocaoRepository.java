package com.feliperaac.gitcommitmirror.repository;

import com.feliperaac.gitcommitmirror.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
    
    // Verifica no Postgres se o ID do produto já foi enviado antes
    boolean existsByProductId(String productId);
}