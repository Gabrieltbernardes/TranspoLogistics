package com.transpologistics.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoCarga;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Motorista motorista;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco origem;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco destino;


    public Entrega() {
    }

    public Entrega(String descricaoCarga, LocalDateTime dataPedido, Cliente cliente, Endereco origem, Endereco destino) {
        this.descricaoCarga = descricaoCarga;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.origem = origem;
        this.destino = destino;
        this.status = StatusEntrega.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoCarga() {
        return descricaoCarga;
    }

    public void setDescricaoCarga(String descricaoCarga) {
        this.descricaoCarga = descricaoCarga;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public Endereco getOrigem() {
        return origem;
    }

    public void setOrigem(Endereco origem) {
        this.origem = origem;
    }

    public Endereco getDestino() {
        return destino;
    }

    public void setDestino(Endereco destino) {
        this.destino = destino;
    }

    public void iniciarTransito() {
        this.status = StatusEntrega.EM_TRANSITO;
    }

    public void finalizarEntrega() {
        this.status = StatusEntrega.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }
}
