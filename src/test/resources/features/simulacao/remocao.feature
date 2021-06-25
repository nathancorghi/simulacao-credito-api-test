#language: pt

Funcionalidade: Remove simulação

  Cenario: Remove uma simuação por id
    Dado Eu tenha um cpf com simulação cadastrada
    Quando Eu chamar a API de remoção de simulação por id
    Então a simulação deverá ser removida com sucesso