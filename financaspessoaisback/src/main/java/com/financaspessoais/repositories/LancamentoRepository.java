package com.financaspessoais.repositories;

import com.financaspessoais.model.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
