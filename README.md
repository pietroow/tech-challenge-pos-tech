<h1 align="center">Tech Challenge Fase 1 - Turma 4SOAT - Grupo 17</h1>
Este projeto é uma aplicação backend construída com uma arquitetura hexagonal (também conhecida como arquitetura porta e adaptador). 
A aplicação utiliza um banco de dados PostgreSQL para armazenar dados relacionados a clientes, produtos, pedidos e pagamentos. O sistema inclui as seguintes APIs:

## :rocket: APIs Disponíveis
**1. Cadastro do Cliente**
<br />Descrição: Permite o cadastro de um novo cliente.
<br />Endpoint: /v1/clientes
<br />Método HTTP: POST
<br />Corpo da Requisição: JSON contendo os detalhes do cliente (nome, CPF, endereço, etc.). Exemplo de Requisição:
```json
{
    "nome": "João da Silva",
    "email": "joao@gmail.com",
    "cpf": "123.456.789-00",
    "senha": "1234",
    "status": "ativo"
}
```
**2. Identificação do Cliente por CPF**
<br />Descrição: Recupera as informações de um cliente com base no CPF.
<br />Endpoint: /api/clientes/{cpf}
<br />Método HTTP: GET
<br />Exemplo de Requisição: /api/clientes/123.456.789-00

**3. Criação, Edição e Remoção de Produto**
<br />Descrição: Permite a criação, edição e remoção de produtos no sistema.
<br />Endpoint: /api/produtos
<br />Método HTTP:
<br />Criação (POST): Cria um novo produto.
<br />Edição (PUT): Atualiza as informações de um produto existente.
<br />Remoção (DELETE): Remove um produto do sistema.
<br />Exemplo de Requisição (Criação):
```json
{
  "nome": "Produto A",
  "categoria": "Eletrônicos",
  "preco": 100.00
}
```
**4. Busca de Produtos por Categoria**
<br />Descrição: Retorna a lista de produtos com base na categoria.
<br />Endpoint: /api/produtos/categoria/{categoria}
<br />Método HTTP: GET
<br />Exemplo de Requisição: /api/produtos/categoria/Eletrônicos

<br />**5. Lista de Pedidos**
<br />Descrição: Retorna a lista de pedidos feitos no sistema.
<br />Endpoint: /api/pedidos
<br />Método HTTP: GET
<br />
<br />**6. Envio de Produtos para Pagamento**
<br />Descrição: Envia os produtos selecionados para pagamento, criando um novo pedido.
<br />Endpoint: /api/pedidos
<br />Método HTTP: POST
<br />Corpo da Requisição: JSON contendo a lista de produtos a serem incluídos no pedido.
<br />Exemplo de Requisição:
```json
{
  "produtos": [
    { "produto_id": 1, "quantidade": 2 },
    { "produto_id": 3, "quantidade": 1 }
  ]
}
```

<h3>Configuração e Execução</h3>
Clone o repositório para sua máquina local.

Certifique-se de ter o Docker instalado e configurado.

Execute o comando:
    docker compose up -d

com isso o aplicativo e o banco de dados são executados. O aplicativo responde na porta 8080 e o banco na porta 5432


Estrutura do Projeto
O projeto segue a arquitetura hexagonal, com uma separação clara entre os domínios, os adaptadores (para APIs externas, como o banco de dados) e as portas (interfaces para as camadas externas).

src/
domain/: Contém a lógica de negócios da aplicação.
adapters/: Contém os adaptadores para o banco de dados, APIs externas, etc.
ports/: Define as interfaces utilizadas para interagir com os adaptadores.
api/: Contém os controladores das APIs mencionadas acima.
Tecnologias Utilizadas
Node.js
Express.js
PostgreSQL
Sequelize (ORM)
Certifique-se de que possui as versões adequadas dessas tecnologias instaladas em seu ambiente.

Contribuindo
Sinta-se à vontade para contribuir para este projeto abrindo problemas ou enviando solicitações pull.
