<h1 align="center" style="font-weight: bold;">Encurta ai 🔗</h1>

<p>
    <b>Este projeto é um backend simples desenvolvido para encurtar URLs. A ideia é permitir que o usuário insira uma URL longa e receba uma URL encurtada. Ao acessar a URL encurtada, o usuário será redirecionado para a URL original.</b>
</p>

## Objetivos do Projeto

- **Estudo e prática de deploy**: Desenvolver habilidades em deploy de aplicações Java.

- **Trabalho com Docker**:
  - Criação de imagens Docker.
  - Disponibilização das imagens no Docker Hub.
  - Configuração de Docker Compose para gerenciar containers, incluindo PostgreSQL e a aplicação.

- **Deploy em diferentes plataformas**:
  - Deploy da aplicação em uma instância EC2 da AWS.
  - Deploy da aplicação no Railway.

## Pré requisitos

- [Docker](https://docs.docker.com/engine/install/).

## Clone o projeto

Execute o comando abaixo para clonar o repositório

```bash
git clone https://github.com/alantrs/encurta-ai
```

## Configure as variaveis de ambiente

Crie os arquivos `app.env` e `postgres.env` e coloque-os em uma pasta nomeada `env` no root do projeto.

***Arquivo `app.env`***

```yaml
SPRING_PROFILES_ACTIVE=prod
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/shorten_it
SPRING_DATASOURCE_USERNAME=seu usuario
SPRING_DATASOURCE_PASSWORD=sua senha
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

***Arquivo `postgres.env`***

```yaml
POSTGRES_DB=shorten_it
POSTGRES_USER=seu usuario
POSTGRES_PASSWORD=sua senha
```

## Iniciar a aplicação

```bash
cd encurta-ai
docker-compose up
```
