package br.com.simulacao.credito.test.restricoes;

import br.com.simulacao.credito.model.response.ResponseData;
import br.com.simulacao.credito.model.response.RestricoesResponse;
import br.com.simulacao.credito.service.RestricoesService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class RestricoesStepDefinitions {

    @Autowired
    private RestricoesService restricoesService;

    private ResponseData<RestricoesResponse> restricoesResponse;

    private String cpf;

    @Dado("Eu tenho um cpf com restrição")
    public void euTenhoUmCpfComRestricao() {

        cpf = "97093236014";
    }

    @Dado("Eu tenho um cpf sem restrição")
    public void euTenhoUmCpfSemRestricao() {

        cpf = "99999999999";
    }

    @Quando("Eu chamar a API de consulta restrição por cpf")
    public void euChamarApiDeConsultaRestricaoPorCpf() throws NoSuchFieldException, IllegalAccessException {

        restricoesResponse = restricoesService.consultaRestricoes(cpf);
    }

    @Entao("deverá ser retornado a restrição com sucesso")
    public void deveraSerRetornadoARestricaoComSucesso() {

        Assert.assertEquals(200, restricoesResponse.getStatusCode());
        Assert.assertNotNull(restricoesResponse.getResponseData().getMensagem());
        Assert.assertTrue(restricoesResponse.getResponseData().getMensagem().contains(cpf));
    }

    @Entao("não deverá retornar nenhuma restrição")
    public void naoDeveraRetornarNenhumaRestricao() {

        Assert.assertEquals(204, restricoesResponse.getStatusCode());
        Assert.assertNull(restricoesResponse.getResponseData());
    }
}