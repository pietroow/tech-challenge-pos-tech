<h1 align="center">Tech Challenge Fase 1 - Turma 4SOAT - Grupo 17</h1>  
Este projeto é uma aplicação backend construída com uma arquitetura hexagonal (também conhecida como arquitetura porta e adaptador).   
A aplicação utiliza um banco de dados PostgreSQL para armazenar dados relacionados a clientes, produtos e pedidos.  

<h3>Configuração e Execução</h3>  
Clone o repositório para sua máquina local.

Certifique-se de ter o Docker instalado e configurado.

Execute o comando: ``` docker compose up -d ``` para subir a aplicação com o banco de dados.

As APIs da aplicação ficam acessíveis no endereço ``` http://localhost:8080 ```


## APIs Disponíveis

As APIs disponíveis são: <br />  
**1. Criação, Edição e Busca do Cliente** <br />  
**2. Criação, Edição, Remoção e Busca de Produto**<br />  
**3. Busca de Produtos por Categoria**<br />  
**4. Criação de pedidos sem identificação do cliente, pedido com o cliente e Busca de Pedidos**<br />

Para utilizar as APIs importe o arquivo: https://github.com/pietroow/tech-challenge-pos-tech/blob/main/Tech-challenge.postman_collection.json no postman

## Implantação no Kubernetes

Esta seção descreve como implantar a aplicação e o banco de dados PostgreSQL no Kubernetes.

### Pré-requisitos

-   Kubernetes cluster
-   `kubectl` configurado
-   Docker (opcional, para construção de imagem personalizada)

### Passos para Implantação

#### 1. Implantar o Banco de Dados PostgreSQL

Implante o PostgreSQL usando o arquivo `postgresql-deployment.yaml`:
````
kubectl apply -f ./k8s/postgresql.yaml
````

#### 2. Implantar a Aplicação

Certifique-se de que a imagem Docker está disponível em um repositório acessível pelo seu cluster Kubernetes. Atualize o arquivo `app-deployment.yaml` com o nome da sua imagem Docker e aplique:
````
kubectl apply -f ./k8s/app.yaml
````

#### 3. Acessar a Aplicação

Encontre o IP e a porta para acessar a aplicação:
````
http://localhost:31800/v1/
````
### Limpeza

Para remover os recursos criados:
````
kubectl delete -f k8s/app-deployment.yaml
kubectl delete -f k8s/postgresql-deployment.yaml
````

# Vídeo explicativo

Segue link do vídeo que explica como a arquitetura e aplicação estão estruturadas e como funciona a implantação.

https://youtu.be/xlN9m0xdUzc