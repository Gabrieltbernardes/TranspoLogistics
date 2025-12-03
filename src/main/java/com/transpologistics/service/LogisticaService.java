package com.transpologistics.service;

import com.transpologistics.model.Entrega;
import com.transpologistics.model.StatusEntrega;

public interface LogisticaService {
    Entrega criarEntrega(Entrega entrega);
    Entrega atualizarStatusEntrega(Long id, StatusEntrega status);
    Entrega rastrearEntrega(Long id);
    Entrega alocarMotorista(Long id, String cpf);
}
