# 🚗 VEND API - Sistema de Vendas de Carros

API REST desenvolvida com Spring Boot para gerenciamento de carros e usuários do sistema VEND, com integração à API FIPE para consulta de preços de veículos.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Executando a Aplicação](#executando-a-aplicação)
- [Documentação da API](#documentação-da-api)
- [Endpoints](#endpoints)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelos de Dados](#modelos-de-dados)

## 📖 Sobre o Projeto

O VEND é um sistema completo para gerenciamento de vendas de carros que oferece:

- ✅ Cadastro e consulta de veículos
- ✅ Gerenciamento de usuários (administradores e clientes)
- ✅ Busca avançada por marca, modelo e ano
- ✅ Integração com API FIPE para preços de mercado
- ✅ Documentação interativa com Swagger/OpenAPI
- ✅ Suporte a diferentes tipos de carroceria
- ✅ Sistema de interesses de clientes

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.6**
  - Spring Web
  - Spring Data JPA
  - Spring Boot Tomcat
- **PostgreSQL** - Banco de dados relacional
- **Springdoc OpenAPI 2.6.0** - Documentação Swagger
- **Google GenAI 1.3.0** - Integração com IA
- **Jackson** - Serialização/Deserialização JSON
- **Maven** - Gerenciamento de dependências

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ☕ Java JDK 21 ou superior
- 📦 Maven 3.6+
- 🐘 PostgreSQL 12+
- 🔧 Git (opcional)

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

Ou no Windows:

```bash
mvnw.cmd clean install
```

## ⚙️ Configuração

### Banco de Dados PostgreSQL

1. Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE vend_db;
```

2. Configure as credenciais de acesso através de variáveis de ambiente:

**Linux/Mac:**
```bash
export DB_HOST=localhost:5432
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
```

**Windows (CMD):**
```cmd
set DB_HOST=localhost:5432
set DB_USER=seu_usuario
set DB_PASSWORD=sua_senha
```

**Windows (PowerShell):**
```powershell
$env:DB_HOST="localhost:5432"
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
```

### Configurações da Aplicação

As configurações podem ser ajustadas no arquivo `src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:postgresql://${DB_HOST}/vend_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

## ▶️ Executando a Aplicação

### Modo Desenvolvimento

```bash
./mvnw spring-boot:run
```

### Gerando e Executando o JAR

```bash
./mvnw clean package
java -jar target/VEND-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: **http://localhost:8080**

## 📚 Documentação da API

Após iniciar a aplicação, acesse:

### 📊 Swagger UI (Interface Interativa)
```
http://localhost:8080/swagger-ui.html
```

### 📄 OpenAPI JSON
```
http://localhost:8080/api-docs
```

## 🔗 Endpoints

### 🚗 Carros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/carros` | Cadastra um novo carro |
| `GET` | `/carros` | Lista todos os carros (ordenados por preço) |
| `GET` | `/carros/{id}` | Busca carro por ID |
| `GET` | `/carros/marca/{marca}` | Busca carros por marca |
| `GET` | `/carros/marca/{marca}/modelo/{modelo}` | Busca por marca e modelo |
| `DELETE` | `/carros/{id}` | Remove um carro |

### 👥 Usuários Administradores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/usuarios` | Cadastra um novo usuário admin |
| `GET` | `/usuarios` | Lista todos os usuários |
| `GET` | `/usuarios/{id}` | Busca usuário por ID |
| `DELETE` | `/usuarios/{id}` | Remove um usuário |

## 📂 Estrutura do Projeto

```
VEND/
├── src/
│   ├── main/
│   │   ├── java/com/example/VEND/
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java           # Configuração CORS
│   │   │   │   └── OpenApiConfig.java        # Configuração Swagger
│   │   │   ├── controller/
│   │   │   │   ├── CarroController.java      # Endpoints de carros
│   │   │   │   └── UsuarioAdmController.java # Endpoints de usuários
│   │   │   ├── dto/
│   │   │   │   ├── CarroCadastrarDTO.java    # DTO para cadastro
│   │   │   │   ├── CarroResponseDTO.java     # DTO de resposta
│   │   │   │   ├── UsuarioCadastrarDTO.java  # DTO cadastro usuário
│   │   │   │   └── UsuarioAdmResponseDTO.java
│   │   │   ├── model/
│   │   │   │   ├── Carro.java                # Entidade Carro
│   │   │   │   ├── UsuarioAdm.java           # Entidade Admin
│   │   │   │   ├── UsuarioCliente.java       # Entidade Cliente
│   │   │   │   ├── DadosFipe.java            # Modelo API FIPE
│   │   │   │   ├── DadosMarca.java
│   │   │   │   ├── DadosLista.java
│   │   │   │   ├── DadosAnos.java
│   │   │   │   └── enums/
│   │   │   │       └── Carroceria.java       # Enum tipos carroceria
│   │   │   ├── repository/
│   │   │   │   ├── RepositorioCarro.java
│   │   │   │   └── RepositorioUsuarioAdm.java
│   │   │   ├── service/
│   │   │   │   ├── CarroService.java         # Lógica de negócio
│   │   │   │   ├── UsuarioAdmService.java
│   │   │   │   ├── ConsumoAPi.java           # Consumo API FIPE
│   │   │   │   └── ConverteJson.java         # Conversão JSON
│   │   │   └── VendApplication.java          # Classe principal
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 📊 Modelos de Dados

### Carro

```json
{
  "id": 1,
  "modelo": "Uno",
  "marca": "fiat",
  "ano": 2023,
  "carroceria": "HATCH",
  "preco": 45000.00,
  "imagem": "byte[]",
  "usuarioCliente": null
}
```

**Tipos de Carroceria:**
- `SEDAN`
- `HATCH`
- `SUV`
- `CAMINHONETE`
- `PERUA`
- `SELECIONE`

### Usuário Administrador

```json
{
  "id": 1,
  "email": "admin@vend.com.br",
  "senha": "senha123"
}
```

### Cadastro de Carro

```json
{
  "carroceria": "hatch",
  "imagem": [byte array],
  "modelo": "uno",
  "ano": 2023,
  "preco": 45000.00,
  "marca": "fiat"
}
```

## 🔍 Exemplos de Uso

### Cadastrar um carro

```bash
curl -X POST "http://localhost:8080/carros" \
  -H "Content-Type: application/json" \
  -d '{
    "carroceria": "hatch",
    "imagem": [],
    "modelo": "uno",
    "ano": 2023,
    "preco": 45000.00,
    "marca": "fiat"
  }'
```

### Listar todos os carros

```bash
curl -X GET "http://localhost:8080/carros"
```

### Buscar carro por ID

```bash
curl -X GET "http://localhost:8080/carros/1"
```

### Buscar carros por marca

```bash
curl -X GET "http://localhost:8080/carros/marca/fiat"
```

### Buscar carros por marca e modelo

```bash
curl -X GET "http://localhost:8080/carros/marca/fiat/modelo/uno"
```

### Deletar um carro

```bash
curl -X DELETE "http://localhost:8080/carros/1"
```

### Cadastrar usuário administrador

```bash
curl -X POST "http://localhost:8080/usuarios" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@vend.com.br",
    "senha": "senha123"
  }'
```

## 🔐 Recursos de Segurança

- Configuração CORS para integração com frontends
- Validação de dados com Bean Validation
- Senhas armazenadas (⚠️ recomenda-se implementar criptografia em produção)

## 🚧 Melhorias Futuras

- [ ] Implementar autenticação JWT
- [ ] Adicionar criptografia de senhas (BCrypt)
- [ ] Sistema de upload de imagens
- [ ] Paginação nos endpoints de listagem
- [ ] Filtros avançados de busca
- [ ] Cache de consultas frequentes
- [ ] Testes unitários e de integração
- [ ] Deploy com Docker

## 📝 Licença

Este projeto está sob a licença MIT.

## 👥 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## 📞 Contato

- **Email:** bekist2006@gmail.com
- **GitHub:** [BKSrn/VEND](https://github.com/BKSrn)

---

Desenvolvido com ☕ pela equipe VEND
