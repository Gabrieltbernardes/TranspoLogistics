package com.transpologistics.controller;

import com.transpologistics.model.Cliente;
import com.transpologistics.model.Motorista;
import com.transpologistics.model.Pessoa;
import com.transpologistics.service.PessoalService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoalService pessoalService;

    public PessoaController(PessoalService pessoalService) {
        this.pessoalService = pessoalService;
    }

    @PostMapping("/motoristas")
    public MotoristaDTO cadastrarMotorista(@RequestBody Motorista motorista) {
        return toMotoristaDTO(pessoalService.cadastrarMotorista(motorista));
    }

    @PostMapping("/clientes")
    public ClienteDTO cadastrarCliente(@RequestBody Cliente cliente) {
        return toClienteDTO(pessoalService.cadastrarCliente(cliente));
    }

    @GetMapping("/{cpfCnpj}")
    public Object consultarPessoa(@PathVariable String cpfCnpj) {
        return Optional.ofNullable(pessoalService.consultarPessoa(cpfCnpj)).map(this::toDTO).orElse(null);
    }

    @PutMapping("/motoristas/{cpf}")
    public MotoristaDTO gerenciarMotorista(@PathVariable String cpf, @RequestBody Motorista motorista) {
        return toMotoristaDTO(pessoalService.gerenciarMotorista(cpf, motorista));
    }

    @PutMapping("/clientes/{cnpj}")
    public ClienteDTO gerenciarCliente(@PathVariable String cnpj, @RequestBody Cliente cliente) {
        return toClienteDTO(pessoalService.gerenciarCliente(cnpj, cliente));
    }

    private Object toDTO(Pessoa pessoa) {
        if (pessoa instanceof Motorista) {
            return toMotoristaDTO((Motorista) pessoa);
        } else if (pessoa instanceof Cliente) {
            return toClienteDTO((Cliente) pessoa);
        }
        return null;
    }

    private MotoristaDTO toMotoristaDTO(Motorista motorista) {
        if (motorista == null) return null;
        MotoristaDTO dto = new MotoristaDTO();
        dto.setId(motorista.getId());
        dto.setNome(motorista.getNome());
        dto.setTelefone(motorista.getTelefone());
        dto.setDataNascimento(motorista.getDataNascimento());
        dto.setCnh(motorista.getCnh());
        dto.setPlacaVeiculo(motorista.getPlacaVeiculo());
        dto.setCpf(motorista.getCpf());
        return dto;
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setTelefone(cliente.getTelefone());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setCnpj(cliente.getCnpj());
        dto.setPrioridade(cliente.isPrioridade());
        dto.setEndereco(cliente.getEndereco());
        return dto;
    }
}
