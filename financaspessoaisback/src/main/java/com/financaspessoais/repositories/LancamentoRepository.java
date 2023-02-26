package com.financaspessoais.repositories;

import com.financaspessoais.model.entities.Lancamento;
import com.financaspessoais.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {


}
