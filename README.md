*Projeto em Spring Boot para gerenciamento de Clientes*
--------------------------------
O projeto tem como objetivo fornecer uma API REST para gerenciar clientes e seus endereços. Ele permitirá operações como:
Adicionar um novo cliente.
Atualizar dados de um cliente.
Adicionar, atualizar e remover endereços associados a um cliente.
Busca clientes e seus endereços.




*O projeto final se encontra em Projeto Ap1*
-----------------------------------------------------------
com descrição do código em "//"



----------------------------------------
*Postman*
------------------------------------

----------------
Criar cliente no banco de Dados 
 --------------------
Post:http://localhost:8080/clientes

{
    "nome": "João da Silva",
    "email": "joao.silva@example.com",
    "cpf": "123.456.789-09",
    "telefone": "(11) 98765-4321",
    "dataNascimento": "1990-01-01",
    "endereco": {
        "logradouro": "Avenida Paulista",
        "numero": "1000",
        "complemento": "Apto 123",
        "bairro": "Bela Vista",
        "cidade": "São Paulo",
        "estado": "SP",
        "cep": "12345-678"
    }
}

------------------------
Buscar todos os clientes
-----------


GET:http://localhost:8080/clientes
{
    "id": 1,
    "nome": "João da Silva Santos",
    "email": "joao.santos@example.com",
    "cpf": "123.456.789-09",
    "telefone": "(11) 98765-4321",
    "dataNascimento": "1990-01-01",
    "endereco": {
        "logradouro": "Avenida Paulista",
        "numero": "1000",
        "complemento": "Apto 123",
        "bairro": "Bela Vista",
        "cidade": "São Paulo",
        "estado": "SP",
        "cep": "12345-678"
    }
}

----------------------------------
Procurar cliente por Id
-----------------------
Get:http://localhost:8080/clientes/23

  "id": 1,
  "nome": "Ana Martins",
  "email": "ana.martins@example.com",
  "cpf": "123.456.789-00",
  "dataNascimento": "1995-04-23",
  "telefone": "(11) 91234-5678",
  "enderecos": [
    {
      "id": 10,
      "rua": "Rua das Palmeiras",
      "numero": "345",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01000-000"
    },
    {
      "id": 11,
      "rua": "Avenida Paulista",
      "numero": "1000",
      "bairro": "Bela Vista",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01310-100"
    }
  ]
}

----------------
excluir cliente por Id
----------------

Del:http://localhost:8080/clientes/24


