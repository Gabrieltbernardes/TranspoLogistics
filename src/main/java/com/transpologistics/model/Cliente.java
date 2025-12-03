package com.transpologistics.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Cliente extends Pessoa {

    private String cnpj;
    private boolean prioridade;
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String nome, String telefone, java.time.LocalDate dataNascimento, String cnpj, boolean prioridade, Endereco endereco, String password) {
        super(nome, telefone, dataNascimento);
        this.cnpj = cnpj;
        this.prioridade = prioridade;
        this.endereco = endereco;
        this.password = password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isPrioridade() {
        return prioridade;
    }

    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
