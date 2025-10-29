package br.com.transpologistics.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

public class Motorista extends Pessoa{

    @Column(unique = true) // CPF deve ser único
    private String cpf;

    private String cnh;
    private String placaVeiculo;

}

