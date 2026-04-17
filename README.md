# 🚀 My Tasks API

A **My Tasks API** é um serviço de backend robusto desenvolvido para o gerenciamento de tarefas (To-Do List). O projeto foi construído utilizando as melhores práticas de desenvolvimento, com foco em segurança, escalabilidade e persistência em nuvem.

Este projeto faz parte do ecossistema **MyTasks**, integrando-se com uma interface mobile desenvolvida em **Flutter**.

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3+
* **Segurança:** Spring Security & JWT (JSON Web Tokens)
* **Persistência de Dados:** Spring Data JPA & Hibernate
* **Banco de Dados:** MySQL (Hospedado via **Aiven.io**)
* **Documentação:** Swagger (OpenAPI 3.0)
* **Containerização:** Docker
* **Hospedagem:** Render

## 📋 Funcionalidades

* **Autenticação:** Sistema de login seguro com geração de tokens JWT.
* <u>**Gestão de Usuários:** Cadastro e controle de perfis.</u> (Work in Progress)
* **CRUD de Tarefas:** Criação, leitura, atualização e deleção de tarefas.
* **Soft Delete:** Tarefas não são excluídas fisicamente do banco de dados imediatamente (campo `active`).
* **Filtros e Paginação:** Listagem de tarefas otimizada para performance.

---

## 🏗️ Arquitetura do Projeto

O projeto segue a arquitetura em camadas para facilitar a manutenção e testes:

1.  **Controller:** Exposição dos endpoints REST.
2.  **Service:** Camada de lógica de negócio.
3.  **Repository:** Interface de comunicação com o banco de dados via JPA.
4.  **Security:** Filtros de autenticação e configuração de protocolos CORS.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* JDK 17
* Maven 3.8+
* Docker

### Configuração de Ambiente
Configure as seguintes variáveis de ambiente:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://<SEU_HOST:<PORTA>/<BANCO>?ssl-mode=REQUIRED
SPRING_DATASOURCE_USERNAME=<SEU_USER>
SPRING_DATASOURCE_PASSWORD=<SUA_SENHA>
API_SECURITY_TOKEN_SECRET=<SUA_SECRET>
```

### Rodando com Maven
```bash
mvn clean install
mvn spring-boot:run
```

### Documentação da API
Após iniciar o servidor, a documentação interativa do Swagger estará disponível em:
`http://localhost:8080/swagger-ui/index.html`

---

## 🌐 Deploy

A API está atualmente hospedada no **Render** e utiliza um banco de dados **MySQL** persistente no **Aiven**.

* **Base URL:** `https://my-tasks-api-oee0.onrender.com`
* **Status:** Operacional, porém, com cold start

---

## 👤 Autor

**Matheus Mendes**
* Estudante de Sistemas de Informação - UFC.

---

> **Nota:** Este projeto foi desenvolvido com foco em demonstrar competências em Backend Engineering, integração com serviços de Cloud e segurança em APIs REST.