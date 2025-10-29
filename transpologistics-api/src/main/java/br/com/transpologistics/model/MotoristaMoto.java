package br.com.transpologistics.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MotoristaMoto extends Motorista {

    private String zonaAtuacao;
    private int entregasDia;
}