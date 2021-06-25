#language: pt

Funcionalidade: Inlusão de simulação

  Cenario: Insere uma simulação com sucesso
    Dado Eu esteja incluindo uma simulação
    Quando Eu chamar a API de inclusão de simulação
    Entao a simulação deverá ser incluída com sucesso

  Cenario: Insere uma simulação para o mesmo cpf
    Dado Eu tenha um cpf com simulação cadastrada
    Quando Eu incluir uma simulação para o mesmo cpf
    Entao deverá ser exibida a mensagem "CPF duplicado"

  Cenario: Valida se campo nome é obrigatório na inclusão de simulação
    Dado Eu esteja incluindo uma simulação sem preencher o campo nome
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "Nome não pode ser vazio"

  Cenario: Valida se campo cpf é obrigatório na inclusão de simulação
    Dado Eu esteja incluindo uma simulação sem preencher o campo cpf
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "CPF não pode ser vazio"

  Cenario: Valida se campo email é obrigatório na inclusão de simulação
    Dado Eu esteja incluindo uma simulação sem preencher o campo email
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "E-mail não deve ser vazio"

  Cenario: Valida se campo valor é obrigatório na inclusão de simulação
    Dado Eu esteja incluindo uma simulação sem preencher o campo valor
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "Valor não pode ser vazio"

  Cenario: Valida se campo parcelas é obrigatório na inclusão de simulação
    Dado Eu esteja incluindo uma simulação sem preencher o campo parcelas
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "Parcelas não pode ser vazio"

  Cenario: Valida limite do campo parcelas na inclusão de simulação
    Dado Eu esteja incluindo uma simulação com parcela única
    Quando Eu chamar a API de inclusão de simulação
    Entao deverá ser exibido o erro "Parcelas deve ser igual ou maior que 2"