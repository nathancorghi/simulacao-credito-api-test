package br.com.simulacao.credito.test.simulacao;

import br.com.simulacao.credito.factory.SimulacaoFactory;
import br.com.simulacao.credito.model.request.SimulacaoRequest;
import br.com.simulacao.credito.model.response.ResponseData;
import br.com.simulacao.credito.model.response.SimulacaoConsultaListaResponse;
import br.com.simulacao.credito.model.response.SimulacaoResponse;
import br.com.simulacao.credito.service.SimulacaoService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SimulacaoStepDefinitions {

    @Autowired
    private SimulacaoFactory simulacaoFactory;

    @Autowired
    private SimulacaoService simulacaoService;

    private SimulacaoRequest simulacaoRequest;

    private SimulacaoRequest simulacaoRequest2;

    private ResponseData<SimulacaoResponse> simulacaoResponse;

    private ResponseData<Object> removeSimulacaoResponse;

    private ResponseData<SimulacaoConsultaListaResponse[]> simulacaoConsultaListaResponse;

    @Dado("Eu esteja incluindo uma simulação")
    public void euEstejaIncluindoUmaSimulacao() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
    }

    @Dado("Eu tenha um cpf com simulação cadastrada")
    public void euTenhaUmCpfComSimulacaoCadastrada() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoResponse = simulacaoService.insereSimulacao(simulacaoRequest);
    }

    @Dado("Eu esteja incluindo uma simulação sem preencher o campo nome")
    public void euEstejaIncluindoUmaSimulacaoSemPreencherOCampoNome() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setNome(null);
    }

    @Dado("Eu esteja incluindo uma simulação sem preencher o campo cpf")
    public void euEstejaIncluindoUmaSimulacaoSemPreencherOCampoCpf() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setCpf(null);
    }

    @Dado("Eu esteja incluindo uma simulação sem preencher o campo email")
    public void euEstejaIncluindoUmaSimulacaoSemPreencherOCampoEmail() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setEmail(null);
    }

    @Dado("Eu esteja incluindo uma simulação sem preencher o campo valor")
    public void euEstejaIncluindoUmaSimulacaoSemPreencherOCampoValor() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setValor(null);
    }

    @Dado("Eu esteja incluindo uma simulação sem preencher o campo parcelas")
    public void euEstejaIncluindoUmaSimulacaoSemPreencherOCampoParcelas() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setParcelas(null);
    }

    @Dado("Eu esteja incluindo uma simulação com parcela única")
    public void euEstejaIncluindoUmaSimulacaoComParcelaUnica() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
        simulacaoRequest.setParcelas(1);
    }

    @Dado("Eu tenha um cpf inexistente")
    public void euTenhaUmCpfInexistente() {

        simulacaoRequest = simulacaoFactory.buildSimulacao();
    }

    @Dado("Eu tenha simulações já cadastradas")
    public void euTenhaSimulacoesJaCadastradas() {

        for(int i=0; i<=3; i++) {

            simulacaoRequest = simulacaoFactory.buildSimulacao();
            simulacaoResponse = simulacaoService.insereSimulacao(simulacaoRequest);
        }
    }

    @Quando("Eu chamar a API de inclusão de simulação")
    public void euChamarApiDeInclusaoDeSimulacao() {

        simulacaoResponse = simulacaoService.insereSimulacao(simulacaoRequest);
    }

    @Quando("Eu incluir uma simulação para o mesmo cpf")
    public void euIncluirUmSimulacaoParaOMesmoCpf() {

        simulacaoRequest2 = simulacaoFactory.buildSimulacao();
        simulacaoRequest2.setCpf(simulacaoRequest.getCpf());
        simulacaoResponse = simulacaoService.insereSimulacao(simulacaoRequest2);
    }

    @Quando("Eu chamar a API de alteração de simulação")
    public void euChamarApiDeAlteracaoDeSimulacao() {

        simulacaoRequest2 = simulacaoFactory.buildSimulacao();
        simulacaoResponse = simulacaoService.alteraSimulacao(simulacaoRequest2, simulacaoRequest.getCpf());
    }

    @Quando("Eu chamar a API de consulta de simulações")
    public void euChamarApiDeConsultaDeSimulacoes() throws NoSuchFieldException, IllegalAccessException {

        simulacaoConsultaListaResponse = simulacaoService.consultaSimulacoes();
    }

    @Quando("Eu chamar a API de consulta de simulações por cpf")
    public void euChamarApiDeConsultaDeSimulacoesPorCpf() throws NoSuchFieldException, IllegalAccessException {

        simulacaoResponse = simulacaoService.consultaSimulacoes(simulacaoRequest.getCpf());
    }

    @Quando("Eu chamar a API de remoção de simulação por id")
    public void euChamarApiDeRemocaoDeSimulacoesPorId() {

        removeSimulacaoResponse = simulacaoService.removeSimulacao(simulacaoResponse.getResponseData().getId());
    }

    @Entao("a simulação deverá ser incluída com sucesso")
    public void aSimulacaoDeveraSerIncluidaComSucesso() {

        Assert.assertEquals(201, simulacaoResponse.getStatusCode());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getId());
        Assert.assertEquals(simulacaoRequest.getNome(), simulacaoResponse.getResponseData().getNome());
        Assert.assertEquals(simulacaoRequest.getCpf(), simulacaoResponse.getResponseData().getCpf());
        Assert.assertEquals(simulacaoRequest.getEmail(), simulacaoResponse.getResponseData().getEmail());
        Assert.assertEquals(simulacaoRequest.getValor(), BigDecimal.valueOf((Double) simulacaoResponse.getResponseData()
                .getValor()).stripTrailingZeros());
        Assert.assertEquals(simulacaoRequest.getParcelas(), Integer.valueOf(new DecimalFormat("#").format(simulacaoResponse.getResponseData().getParcelas())));
        Assert.assertEquals(simulacaoRequest.getSeguro(), simulacaoResponse.getResponseData().getSeguro());
    }

    @Entao("deverá ser exibida a mensagem {string}")
    public void deveraSerExibidaAMensagem(String mensagem) {

        Assert.assertEquals(400, simulacaoResponse.getStatusCode());
        Assert.assertEquals(mensagem, simulacaoResponse.getMensagem());
    }

    @Entao("deverá ser exibido o erro {string}")
    public void deveraSerExibidoOErro(String erro) throws IllegalAccessException {

        Field[] fields = simulacaoResponse.getErros().getClass().getSuperclass().getDeclaredFields();

        String valorPropriedade;
        List<String> valoresPropriedades = new ArrayList<>();

        for(Field field : fields) {

            field.setAccessible(true);
            valorPropriedade = (String) field.get(simulacaoResponse.getErros());

            if (valorPropriedade != null) {

                valoresPropriedades.add(valorPropriedade);
            }
        }

        Assert.assertEquals(400, simulacaoResponse.getStatusCode());
        Assert.assertEquals(erro, valoresPropriedades.stream().findFirst().get());
        Assert.assertEquals(1, valoresPropriedades.size());
    }

    @Entao("a simulação deverá ser alterada com sucesso")
    public void aSimulacaoDeveraSerAltreradaComSucesso() {

        Assert.assertEquals(200, simulacaoResponse.getStatusCode());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getId());
        Assert.assertEquals(simulacaoRequest2.getNome(), simulacaoResponse.getResponseData().getNome());
        Assert.assertEquals(simulacaoRequest2.getCpf(), simulacaoResponse.getResponseData().getCpf());
        Assert.assertEquals(simulacaoRequest2.getEmail(), simulacaoResponse.getResponseData().getEmail());
        Assert.assertEquals(simulacaoRequest2.getParcelas(), Integer.valueOf(new DecimalFormat("#").format(simulacaoResponse.getResponseData().getParcelas())));
        Assert.assertEquals(simulacaoRequest2.getSeguro(), simulacaoResponse.getResponseData().getSeguro());
    }

    @Entao("deverá ser exibida a mensagem de validação de cpf")
    public void deveraSerExibidaAMensagemDeValidacaoDeCpf() {

        Assert.assertEquals(404, simulacaoResponse.getStatusCode());
        Assert.assertEquals("CPF " + simulacaoRequest.getCpf() + " não encontrado", simulacaoResponse.getMensagem());
    }

    @Entao("deverão ser retornadas as simulações cadastradas")
    public void deveraoSerRetornadasAsSimulacoesCadastradas() {

        Assert.assertEquals(200, simulacaoConsultaListaResponse.getStatusCode());
    }

    @Entao("deverá ser retornada a situação do cpf")
    public void deveraSerRetornadaASituacaoDoCpf() {

        Assert.assertEquals(200, simulacaoResponse.getStatusCode());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getId());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getNome());
        Assert.assertEquals(simulacaoRequest.getCpf(), simulacaoResponse.getResponseData().getCpf());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getEmail());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getValor());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getParcelas());
        Assert.assertNotNull(simulacaoResponse.getResponseData().getSeguro());
    }

    @Entao("a simulação deverá ser removida com sucesso")
    public void aSimulacaoDeveraSerRemovidaComSucesso() {

        Assert.assertEquals(200, removeSimulacaoResponse.getStatusCode());
        Assert.assertEquals("OK", removeSimulacaoResponse.getResponseData());
    }
}