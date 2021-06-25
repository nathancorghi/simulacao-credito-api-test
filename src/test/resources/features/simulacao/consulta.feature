#language: pt

Funcionalidade: Consulta simulações

  Cenario: Consulta todas simulações
    Dado Eu tenha simulações já cadastradas
    Quando Eu chamar a API de consulta de simulações
    Entao deverão ser retornadas as simulações cadastradas

  Cenario: Consulta simulação por cpf
    Dado Eu tenha um cpf com simulação cadastrada
    Quando Eu chamar a API de consulta de simulações por cpf
    Entao deverá ser retornada a situação do cpf

  Cenario: Consulta simulação por cpf inexistente
    Dado Eu tenha um cpf inexistente
    Quando Eu chamar a API de consulta de simulações por cpf
    Entao deverá ser exibida a mensagem de validação de cpf