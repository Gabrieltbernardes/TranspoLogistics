package com.transpologistics.service;

import com.transpologistics.model.Cliente;
import com.transpologistics.model.Motorista;
import com.transpologistics.model.Pessoa;
import com.transpologistics.repository.ClienteRepository;
import com.transpologistics.repository.MotoristaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PessoalServiceImpl implements PessoalService {

    private final MotoristaRepository motoristaRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PessoalServiceImpl(MotoristaRepository motoristaRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Motorista cadastrarMotorista(Motorista motorista) {
        motorista.setPassword(passwordEncoder.encode(motorista.getPassword()));
        return motoristaRepository.save(motorista);
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    @Override
    public Pessoa consultarPessoa(String cpfCnpj) {
        return motoristaRepository.findByCpf(cpfCnpj)
                .map(Pessoa.class::cast)
                .orElse(clienteRepository.findByCnpj(cpfCnpj).orElse(null));
    }

    @Override
    public Motorista gerenciarMotorista(String cpf, Motorista motorista) {
        return motoristaRepository.findByCpf(cpf).map(m -> {
            m.setNome(motorista.getNome());
            m.setTelefone(motorista.getTelefone());
            m.setDataNascimento(motorista.getDataNascimento());
            m.setCnh(motorista.getCnh());
            m.setPlacaVeiculo(motorista.getPlacaVeiculo());
            if (motorista.getPassword() != null && !motorista.getPassword().isEmpty()) {
                m.setPassword(passwordEncoder.encode(motorista.getPassword()));
            }
            return motoristaRepository.save(m);
        }).orElse(null);
    }

    @Override
    public Cliente gerenciarCliente(String cnpj, Cliente cliente) {
        return clienteRepository.findByCnpj(cnpj).map(c -> {
            c.setNome(cliente.getNome());
            c.setTelefone(cliente.getTelefone());
            c.setDataNascimento(cliente.getDataNascimento());
            c.setPrioridade(cliente.isPrioridade());
            c.setEndereco(cliente.getEndereco());
            if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
                c.setPassword(passwordEncoder.encode(cliente.getPassword()));
            }
            return clienteRepository.save(c);
        }).orElse(null);
    }
}
