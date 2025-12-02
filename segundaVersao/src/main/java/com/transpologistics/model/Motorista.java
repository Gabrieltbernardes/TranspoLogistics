package com.transpologistics.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Motorista extends Pessoa {

    private String cnh;
    private String placaVeiculo;
    private String cpf;
    private String password;


    public Motorista() {
    }

    public Motorista(String nome, String telefone, LocalDate dataNascimento, String cnh, String placaVeiculo, String cpf, String password) {
        super(nome, telefone, dataNascimento);
        this.cnh = cnh;
        this.placaVeiculo = placaVeiculo;
        this.cpf = cpf;
        this.password = password;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
