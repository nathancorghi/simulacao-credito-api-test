package br.com.simulacao.credito.model.request;

import br.com.simulacao.credito.model.SimulacaoData;
import lombok.Builder;

import java.math.BigDecimal;

public class SimulacaoRequest extends SimulacaoData<Object> {

    @Builder
    public SimulacaoRequest(String nome, String cpf, String email, BigDecimal valor, Integer parcelas, Boolean seguro) {
        super(nome, cpf, email, valor, parcelas, seguro);
    }
}