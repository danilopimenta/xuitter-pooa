# Xuitter API

## Descrição
A Xuitter API é uma aplicação de rede social similar ao Twitter, construída utilizando Spring Boot, Maven e PostgreSQL.

## Requisitos
- Java 11 ou superior
- Maven
- Docker (opcional)

## Instalação

### Com Docker
1. Clone o repositório:
    ```bash
    git clone https://github.com/danilopimenta/xuitter.git
    cd xuitter
    ```

2. Construa e inicie os containers Docker:
    ```bash
    docker-compose up --build
    ```

3. Acesse a aplicação em `http://localhost:8888`.

### Sem Docker
1. Clone o repositório:
    ```bash
    git clone https://github.com/danilopimenta/xuitter.git
    cd xuitter
    ```

2. Configure o banco de dados PostgreSQL:
    - Crie um banco de dados chamado `xuitter`.
    - Atualize as credenciais do banco de dados no arquivo `src/main/resources/application.properties`.

3. Construa e execute a aplicação:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. Acesse a aplicação em `http://localhost:8080`.

## Endpoints

### Autenticação
- **POST** `/auth/register`: Registra um novo usuário.
- **POST** `/auth/login`: Autentica um usuário e retorna um token.

### Usuários
- **DELETE** `/users/{id}`: Deleta um usuário.
- **PUT** `/users/{id}`: Atualiza um usuário.

### Tweets
- **POST** `/tweets`: Cria um novo tweet.
- **GET** `/tweets/{id}`: Retorna um tweet específico.
- **DELETE** `/tweets/{id}`: Deleta um tweet.
- **PUT** `/tweets/{id}`: Atualiza um tweet.

### Likes
- **POST** `/likes/tweet/{tweetId}`: Dá like em um tweet.
- **DELETE** `/likes/tweet/{tweetId}`: Remove o like de um tweet.

### Seguidores
- **GET** `/followers/user/{id}`: Lista os seguidores de um usuário.
- **POST** `/followers/follow/user/{userToFollow}`: Segue um usuário.
- **POST** `/followers/unfollow/user/{userToUnfollow}`: Deixa de seguir um usuário.

### Xuitter
- **GET** `/`: Lista os tweets recentes.
- **GET** `/{username}`: Lista os tweets de um usuário específico.

## Documentação OpenAPI
A documentação OpenAPI pode ser encontrada no arquivo `openapi.yaml` no diretório raiz do projeto.