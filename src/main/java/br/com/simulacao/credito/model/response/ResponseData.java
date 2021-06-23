package br.com.simulacao.credito.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> {

    private T responseData;

    private int statusCode;
}