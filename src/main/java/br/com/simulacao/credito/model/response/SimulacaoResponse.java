package br.com.simulacao.credito.model.response;

import br.com.simulacao.credito.model.SimulacaoData;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SimulacaoResponse extends SimulacaoData<Object> {

    private Integer id;

    public SimulacaoResponse(String nome, String cpf, String email, BigDecimal valor, Integer parcelas, Boolean seguro) {
        super(nome, cpf, email, valor, parcelas, seguro);
    }
}