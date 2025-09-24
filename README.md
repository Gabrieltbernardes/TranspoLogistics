# TranspoLogistics
Projeto que simula uma transportadora.
```mermaid

classDiagram
    direction TD
    class TranspoLogistics {
        +cadastrarMotorista(dados) Motorista
        +cadastrarCliente(dados) Cliente
        +consultarPessoa(cpf_cnpj) Pessoa
        +criarEntrega(dados) Entrega
        +atualizarStatusEntrega(id, status)
    }

    TranspoLogistics --|> PessoalService
    TranspoLogistics --|> LogisticaService

    <<Interface>> PessoalService
    PessoalService : +cadastrarMotorista(dados) Motorista
    PessoalService : +cadastrarCliente(dados) Cliente
    PessoalService : +consultarPessoa(cpf_cnpj) Pessoa

    <<Interface>> LogisticaService
    LogisticaService : +criarEntrega(dados) Entrega
    LogisticaService : +atualizarStatusEntrega(id, status)
    LogisticaService : +rastrearEntrega(id) Entrega

    class Pessoa {
        <<Abstract>>
        -nome: String
        -telefone: String
        -dataNascimento: LocalDate
        +getIdade() int
    }

    class Motorista {
        -cnh: String
        -placaVeiculo: String
        -cpf: String
    }
    
    class MotoristaCarro {
        -rotaDesignada: String
        -capacidadeCarga: double
    }

    class MotoristaMoto {
        -zonaAtuacao: String
        -entregasDia: int
    }
    
    class Cliente {
        -cnpj: String
        -prioridade: boolean
    }

    class Endereco {
        -rua: String
        -numero: String
        -bairro: String
        -cidade: String
        -cep: String
    }

    class Entrega {
      -id: Long
      -descricaoCarga: String
      -dataPedido: LocalDateTime
      -dataEntrega: LocalDateTime
      +iniciarTransito()
      +finalizarEntrega()
    }

    class StatusEntrega {
        <<Enumeration>>
        PENDENTE
        EM_TRANSITO
        ENTREGUE
        CANCELADA
    }

    Pessoa <|-- Motorista
    Pessoa <|-- Cliente
    Motorista <|-- MotoristaCarro
    Motorista <|-- MotoristaMoto

    Cliente "1" *-- "1" Endereco : possui
    
    Entrega "N" --> "1" Cliente : solicitada por
    Entrega "N" --> "1" Motorista : alocada para
    Entrega "1" --> "1" StatusEntrega : tem status
    Entrega "1" --> "2" Endereco : tem origem e destino
