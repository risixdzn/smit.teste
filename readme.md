# SMIT CRUD

API REST para gerenciamento de **Produtos** e **Pedidos**.

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)](#) [![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=fff)](#) [![Swagger](https://img.shields.io/badge/Swagger-82e82c?logo=swagger&logoColor=000)](#)

## 🔧 Tecnologias

* Java 17
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* MapStruct
* Bean Validation (jakarta.validation)
* Lombok
* Swagger/OpenAPI (springdoc)
* MySQL 8.3

## 🚀 Pré-requisitos

* Docker & Docker Compose
* JDK 17+
* Maven ou Gradle

## 🛠️ Instalação e execução

1. Clone o repositório:

```bash
git clone https://github.com/risixdzn/smit.teste.git
cd smit.teste
```

2. Renomeie o arquivo de ambiente e configure as variáveis (ou use o `.env.example` como base):

```bash
mv .env.example .env
# Edite .env para ajustar DB_ROOT_PASSWORD, DB_DATABASE, DB_USER, DB_PASSWORD
```

3. Inicie o banco de dados via Docker:

```bash
docker-compose up -d
```

4. Compile e execute a aplicação:

```bash
mvn spring-boot:run
# ou
./mvnw spring-boot:run
```

- Na IDE (JetBrains): selecione a classe `CrudApplication` e clique em ▶️ Run, ou use a configuração Maven "
spring-boot\:run".

A API estará disponível em `http://localhost:8080`.

## 📄 Documentação Swagger / OpenAPI

Acesse em:

```
http://localhost:8080/api/reference
```

## 🍃 Endpoints principais

* **Produtos:** `/api/produtos`
* **Pedidos:** `/api/pedidos`

## 📝 Regras de negócio

* **Validação de estoque** antes de criar pedido; retorna 422 se insuficiente.
* **Atualização de estoque** ao confirmar pedido.
* Cálculo de total do pedido via método `getTotal()`.

## 🐳 Docker Compose

Arquivo `docker-compose.yml` utilizado:

```yaml
version: '3.8'

services:
  db:
    image: mysql:8.3.0-oraclelinux8
    container_name: smit_mysql
    restart: always
    env_file:
      - .env
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_ROOT_PASSWORD}
      MYSQL_DATABASE: ${DB_DATABASE}
      MYSQL_USER: ${DB_USER}
      MYSQL_PASSWORD: ${DB_PASSWORD}
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
```