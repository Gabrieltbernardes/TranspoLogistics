package br.com.transpologistics.model;

import br.com.transpologistics.model.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoCarga;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;

    // Relacionamento UML: Entrega "1" --> "1" StatusEntrega (Enum)
    @Enumerated(EnumType.STRING) // Armazena "PENDENTE" em vez de 0, 1, 2...
    private StatusEntrega status;

    // Relacionamento UML: Entrega "N" --> "1" Cliente
    @ManyToOne(fetch = FetchType.LAZY) // LAZY é boa prática para performance
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento UML: Entrega "N" --> "1" Motorista
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    // Relacionamento UML: Entrega "1" --> "2" Endereco (Origem e Destino)
    // Assumimos que o endereço pode ser criado junto com a entrega (CascadeType.PERSIST)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "endereco_origem_id")
    private Endereco enderecoOrigem;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "endereco_destino_id")
    private Endereco enderecoDestino;

    // Métodos do UML (Lógica de negócio, não persistência)
    public void iniciarTransito() {
        this.status = StatusEntrega.EM_TRANSITO;
    }

    public void finalizarEntrega() {
        this.status = StatusEntrega.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }
}
