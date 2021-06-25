#language: pt

Funcionalidade: Alteração de simulação

  Cenario: Altera uma simulação com sucesso
    Dado Eu tenha um cpf com simulação cadastrada
    Quando Eu chamar a API de alteração de simulação
    Entao a simulação deverá ser alterada com sucesso

  Cenario: Valida cpf inexistente
    Dado Eu tenha um cpf inexistente
    Quando Eu chamar a API de alteração de simulação
    Entao deverá ser exibida a mensagem de validação de cpf