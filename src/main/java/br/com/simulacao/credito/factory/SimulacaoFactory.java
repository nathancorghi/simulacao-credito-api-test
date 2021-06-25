package br.com.simulacao.credito.factory;

import br.com.simulacao.credito.model.request.SimulacaoRequest;
import br.com.simulacao.credito.utils.FakerUtils;
import br.com.simulacao.credito.utils.NormalizerUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class SimulacaoFactory {

    public SimulacaoRequest buildSimulacao() {

        Random random = new Random();

        final String nome = FakerUtils.geraNome() + " " + FakerUtils.geraSobrenome();

        return SimulacaoRequest.builder()
                .nome(nome)
                .cpf(FakerUtils.geraCpf())
                .email(NormalizerUtils.retornaTextoSemAcentuacaoEspacoEMinusculo(nome) + "@gmail.com")
                .valor(new BigDecimal(random.nextInt((1000 - 100) + 1) + 100))
                .parcelas(random.nextInt((10 - 2) + 1) + 2)
                .seguro(new Random().nextBoolean())
                .build();
    }
}