package com.transpologistics.repository;

import com.transpologistics.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByCpf(String cpf);
}
