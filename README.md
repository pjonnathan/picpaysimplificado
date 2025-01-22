# Sistema de Cadastro de Usuários e Transações

## Descrição do Projeto

Este é um projeto desenvolvido em **Java** utilizando o framework **Spring Boot**. O objetivo principal é gerenciar usuários e realizar transações financeiras entre eles. O sistema foi projetado com as seguintes características principais:

- Cadastro de usuários.
- Realização de transações entre usuários comuns e de usuários comuns para empresas.
- Restrições: transações de empresas para usuários comuns não são permitidas.
- Banco de dados em memória **H2**.
- Tratamento de exceções para entradas inválidas, como usuários com documentos duplicados.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.0**
  - Spring Web
  - Spring Data JPA
- **H2 Database**
- **Maven**
- **RESTful API**

---

## Endpoints da API

### Usuários

#### Cadastro de Usuário
`POST /users`

```json
{
  "name": "João Silva",
  "document": "12345678901",
  "email": "joao.silva@email.com",
  "balance": 1000.00,
  "type": "common"
}
```

#### Consulta de Usuário por ID
`GET /users/{id}`

### Transações

#### Realizar Transação
`POST /transactions`

```json
{
  "senderId": 1,
  "receiverId": 2,
  "value": 100.00
}
```

---

## Regras de Negócio

- **Tipos de Usuários**: `common` (usuário comum) e `company` (empresa).
- Transações permitidas:
  - De usuário comum para outro usuário comum.
  - De usuário comum para empresas.
- Transações **não permitidas**:
  - De empresas para usuários comuns.

---

## Banco de Dados

O projeto utiliza o banco de dados **H2** para facilitar o desenvolvimento e os testes. O console do H2 pode ser acessado em: `http://localhost:8080/h2-console`.

Configurações padrão do H2 (em `application.properties`):

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

---

## Tratamento de Exceções

O projeto implementa um sistema de tratamento de exceções para:

- Usuários com documentos duplicados.
- Transações não autorizadas.
- Saldo insuficiente para realizar uma transação.

---

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd seu-repositorio
   ```

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação em `http://localhost:8080`.

---

## Melhorias Futuras

- Implementação de autenticação e autorização com Spring Security.
- Integração com um gateway de pagamentos.
- Melhorias na interface de tratamento de erros.

---

## Autor

Desenvolvido por **Pedro Jonnathan**.

- [LinkedIn](https://linkedin.com/in/pedro-jonnathan-bb8870323/)
- [GitHub](https://github.com/pjonnathan)
