package br.com.simulacao.credito.service;

import br.com.simulacao.credito.model.request.SimulacaoRequest;
import br.com.simulacao.credito.model.response.ResponseData;
import br.com.simulacao.credito.model.response.SimulacaoConsultaListaResponse;
import br.com.simulacao.credito.model.response.SimulacaoResponse;
import org.springframework.stereotype.Service;

@Service
public class SimulacaoService extends AbstractService {

    private static final String URL_SIMULACAO = "/api/v1/simulacoes";

    private static final String URL_SIMULACAO_PARAMS = "/api/v1/simulacoes/{0}";

    public ResponseData<SimulacaoResponse> insereSimulacao(SimulacaoRequest simulacaoRequest) {

        return requestUtils.post(URL_SIMULACAO, simulacaoRequest, SimulacaoResponse.class);
    }

    public ResponseData<SimulacaoResponse> alteraSimulacao(SimulacaoRequest simulacaoRequest, String cpf) {

        return requestUtils.put(URL_SIMULACAO_PARAMS, simulacaoRequest, SimulacaoResponse.class, cpf);
    }

    public ResponseData<SimulacaoConsultaListaResponse[]> consultaSimulacoes() throws NoSuchFieldException, IllegalAccessException {

        return requestUtils.get(URL_SIMULACAO, SimulacaoConsultaListaResponse[].class);
    }

    public ResponseData<SimulacaoResponse> consultaSimulacoes(String cpf) throws NoSuchFieldException, IllegalAccessException {

        return requestUtils.get(URL_SIMULACAO_PARAMS, SimulacaoResponse.class, cpf);
    }

    public ResponseData<Object> removeSimulacao(Integer id) {

        return requestUtils.delete(URL_SIMULACAO_PARAMS, Object.class, id);
    }
}