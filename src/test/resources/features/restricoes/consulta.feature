#language: pt

Funcionalidade: Consulta restrição de um CPF

  Cenario: Consulta cpf com restrição
    Dado Eu tenho um cpf com restrição
    Quando Eu chamar a API de consulta restrição por cpf
    Entao deverá ser retornado a restrição com sucesso

  Cenario: Consulta sem restrição
    Dado Eu tenho um cpf sem restrição
    Quando Eu chamar a API de consulta restrição por cpf
    Entao não deverá retornar nenhuma restrição