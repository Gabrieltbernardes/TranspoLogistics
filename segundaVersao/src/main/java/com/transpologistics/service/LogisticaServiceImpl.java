package com.transpologistics.service;

import com.transpologistics.model.Entrega;
import com.transpologistics.model.StatusEntrega;
import com.transpologistics.repository.EntregaRepository;
import com.transpologistics.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

@Service
public class LogisticaServiceImpl implements LogisticaService {

    private final EntregaRepository entregaRepository;
    private final MotoristaRepository motoristaRepository;

    public LogisticaServiceImpl(EntregaRepository entregaRepository, MotoristaRepository motoristaRepository) {
        this.entregaRepository = entregaRepository;
        this.motoristaRepository = motoristaRepository;
    }

    @Override
    public Entrega criarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Override
    public Entrega atualizarStatusEntrega(Long id, StatusEntrega status) {
        return entregaRepository.findById(id).map(entrega -> {
            entrega.setStatus(status);
            if (status == StatusEntrega.EM_TRANSITO) {
                entrega.iniciarTransito();
            } else if (status == StatusEntrega.ENTREGUE) {
                entrega.finalizarEntrega();
            }
            return entregaRepository.save(entrega);
        }).orElse(null);
    }

    @Override
    public Entrega rastrearEntrega(Long id) {
        return entregaRepository.findById(id).orElse(null);
    }

    @Override
    public Entrega alocarMotorista(Long id, String cpf) {
        return entregaRepository.findById(id).map(entrega -> {
            return motoristaRepository.findByCpf(cpf).map(motorista -> {
                entrega.setMotorista(motorista);
                return entregaRepository.save(entrega);
            }).orElse(null);
        }).orElse(null);
    }
}
