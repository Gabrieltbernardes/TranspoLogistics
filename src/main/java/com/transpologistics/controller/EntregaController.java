package com.transpologistics.controller;

import com.transpologistics.model.Entrega;
import com.transpologistics.model.StatusEntrega;
import com.transpologistics.service.LogisticaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final LogisticaService logisticaService;

    public EntregaController(LogisticaService logisticaService) {
        this.logisticaService = logisticaService;
    }

    @PostMapping
    public Entrega criarEntrega(@RequestBody Entrega entrega) {
        return logisticaService.criarEntrega(entrega);
    }

    @PutMapping("/{id}/status")
    public Entrega atualizarStatusEntrega(@PathVariable Long id, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        return logisticaService.atualizarStatusEntrega(id, statusUpdateRequest.getStatus());
    }

    @GetMapping("/{id}")
    public Entrega rastrearEntrega(@PathVariable Long id) {
        return logisticaService.rastrearEntrega(id);
    }

    @PutMapping("/{id}/alocar")
    public Entrega alocarMotorista(@PathVariable Long id, @RequestBody AlocacaoRequest alocacaoRequest) {
        return logisticaService.alocarMotorista(id, alocacaoRequest.getCpf());
    }
}

class StatusUpdateRequest {
    private StatusEntrega status;

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }
}

class AlocacaoRequest {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
