package br.com.transpologistics.repository;

import br.com.transpologistics.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Queries customizadas para Pessoa (se necessário)
}
