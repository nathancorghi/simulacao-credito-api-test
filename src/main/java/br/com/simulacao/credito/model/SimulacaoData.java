package br.com.simulacao.credito.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoData<T> {

    private String nome;

    private String cpf;

    private String email;

    private T valor;

    private T parcelas;

    private Boolean seguro;
}