package br.com.transpologistics.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data // Inclui Getter, Setter, Equals, HashCode, ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    // Método do UML (não persistido)
    @Transient // Indica ao JPA para não mapear este campo no banco
    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return java.time.Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

}
