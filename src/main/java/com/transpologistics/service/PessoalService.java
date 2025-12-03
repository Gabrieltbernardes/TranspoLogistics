package com.transpologistics.service;

import com.transpologistics.model.Cliente;
import com.transpologistics.model.Motorista;
import com.transpologistics.model.Pessoa;

public interface PessoalService {
    Motorista cadastrarMotorista(Motorista motorista);
    Cliente cadastrarCliente(Cliente cliente);
    Pessoa consultarPessoa(String cpfCnpj);
    Motorista gerenciarMotorista(String cpf, Motorista motorista);
    Cliente gerenciarCliente(String cnpj, Cliente cliente);
}
