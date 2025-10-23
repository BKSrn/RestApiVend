# 🚗 VEND API - Sistema de Vendas de Carros

API REST desenvolvida com Spring Boot para gerenciamento de carros e usuários do sistema VEND.

## 📋 Índice

- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Executando a Aplicação](#executando-a-aplicação)
- [Documentação da API](#documentação-da-api)
- [Endpoints](#endpoints)
- [Estrutura do Projeto](#estrutura-do-projeto)

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **PostgreSQL**
- **Springdoc OpenAPI 2.6.0** (Swagger)
- **Google GenAI 1.3.0**
- **Maven**

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- Java JDK 21 ou superior
- Maven 3.6+
- PostgreSQL 12+
- Git (opcional)

## 🚀 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/VEND.git
cd VEND
```

### 2. Instale as dependências

```bash
./mvnw clean install
```

## ⚙️ Configuração

### Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE vend_db;
```

### Variáveis de Ambiente

Configure as seguintes variáveis de ambiente:

```bash
# Linux/Mac
export DB_HOST=localhost:5432
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha

# Windows (CMD)
set DB_HOST=localhost:5432
set DB_USER=seu_usuario
set DB_PASSWORD=sua_senha

# Windows (PowerShell)
$env:DB_HOST="localhost:5432"
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
```

Ou edite o arquivo `src/main/resources/application.properties` diretamente.

## ▶️ Executando a Aplicação

### Modo Desenvolvimento

```bash
./mvnw spring-boot:run
```

### Gerando o JAR

```bash
./mvnw clean package
java -jar target/VEND-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Após iniciar a aplicação, acesse a documentação interativa do Swagger:

### Swagger UI (Interface Gráfica)
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON
```
http://localhost:8080/api-docs
```

## 🔗 Endpoints

### Carros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/carros` | Lista todos os carros |
| GET | `/carros/{id}` | Busca carro por ID |
| GET | `/carros/marca/{marca}` | Busca carros por marca |
| GET | `/carros/marca/{marca}/modelo/{modelo}` | Busca por marca e modelo |
| DELETE | `/carros/{id}` | Deleta um carro |

### Usuários Administradores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Busca usuário por ID |
| DELETE | `/usuarios/{id}` | Deleta um usuário |

## 📂 Estrutura do Projeto

```
VEND/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/VEND/
│   │   │       ├── config/          # Configurações
│   │   │       │   └── OpenApiConfig.java
│   │   │       ├── controller/      # Controllers REST
│   │   │       │   ├── CarroController.java
│   │   │       │   └── UsuarioAdmController.java
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       │   ├── CarroDTO.java
│   │   │       │   └── UsuarioAdmDTO.java
│   │   │       ├── model/           # Entidades JPA
│   │   │       │   ├── Carro.java
│   │   │       │   ├── Carroceria.java
│   │   │       │   ├── UsuarioAdm.java
│   │   │       │   └── UsuarioCliente.java
│   │   │       ├── repository/      # Repositórios JPA
│   │   │       │   ├── RepositorioCarro.java
│   │   │       │   └── RepositorioUsuarioAdm.java
│   │   │       ├── service/         # Lógica de negócio
│   │   │       │   ├── CarroService.java
│   │   │       │   ├── UsuarioAdmService.java
│   │   │       │   ├── ConsumoAPi.java
│   │   │       │   └── ConverteJson.java
│   │   │       └── VendApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 🔍 Exemplos de Uso

### Listar todos os carros

```bash
curl -X GET "http://localhost:8080/carros" -H "accept: application/json"
```

### Buscar carro por ID

```bash
curl -X GET "http://localhost:8080/carros/1" -H "accept: application/json"
```

### Buscar carros por marca

```bash
curl -X GET "http://localhost:8080/carros/marca/fiat" -H "accept: application/json"
```

### Deletar um carro

```bash
curl -X DELETE "http://localhost:8080/carros/1"
```

## 📊 Modelos de Dados

### CarroDTO

```json
{
  "id": 1,
  "modelo": "Uno",
  "marca": "Fiat",
  "ano": 2023,
  "carroceria": "HATCH",
  "preco": 45000.00,
  "usuarioClienteId": null
}
```

### UsuarioAdmDTO

```json
{
  "id": 1,
  "email": "admin@vend.com.br",