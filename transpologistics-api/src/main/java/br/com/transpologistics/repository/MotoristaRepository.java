package br.com.transpologistics.repository;

import br.com.transpologistics.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByCpf(String cpf);
}
