package br.com.simulacao.credito.service;

import br.com.simulacao.credito.model.response.ResponseData;
import br.com.simulacao.credito.model.response.RestricoesResponse;
import org.springframework.stereotype.Service;

@Service
public class RestricoesService extends AbstractService {

    private static final String URL_CONSULTA_RESTRICOES = "/api/v1/restricoes/{0}";

    public ResponseData<RestricoesResponse> consultaRestricoes(String cpf) {

        return requestUtils.get(URL_CONSULTA_RESTRICOES, RestricoesResponse.class, cpf);
    }
}