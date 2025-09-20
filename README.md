# 📚 Desafio LMS – Learning Management System

Um sistema de gerenciamento de aprendizagem (**LMS**) desenvolvido em **Java 21** com **Spring Boot**.  
A aplicação permite gerenciar pessoas, cursos, matrículas, tarefas e autenticação de usuários.

---

## 🚀 Tecnologias utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Maven**
- **Docker & Docker Compose**
- **OpenAPI (Swagger UI)**

---

## ⚙️ Funcionalidades
- Cadastro e autenticação de usuários com JWT
- Gestão de cursos, categorias e tarefas
- Matrícula de pessoas em cursos
- Documentação da API interativa via Swagger

---

## 🐳 Rodando com Docker

1. **Clonar o repositório**
```bash
git clone https://github.com/DaliSant948/desafio-lms.git
cd desafio-lms
Subir containers (backend + banco)

bash
Copy code
docker-compose up -d --build
Verificar containers ativos

bash
Copy code
docker ps
Parar os containers

bash
Copy code
docker-compose down
🔗 A aplicação backend estará rodando em:
👉 http://localhost:8080

📖 Swagger – Documentação da API
Após rodar o projeto, acesse:
👉 http://localhost:8080/swagger-ui.html

No Swagger você poderá:

Visualizar todos os endpoints organizados por controller

Fazer login e gerar um JWT Token para testar endpoints protegidos

Executar requisições (GET, POST, PUT, DELETE) diretamente da interface

🔑 Autenticação no Swagger
No endpoint /auth/login, faça login com usuário e senha cadastrados.

Copie o token JWT retornado.

Clique no botão "Authorize" no canto superior direito do Swagger.

Cole o token no formato:

php-template
Copy code
Bearer <seu_token_aqui>
Agora todos os endpoints protegidos estarão habilitados para teste direto pelo Swagger.

✅ Checklist de Desenvolvimento
1️⃣ Banco de Dados

 Revisar tabelas criadas: Pessoa, Curso, Matricula, Tarefa, Categoria

 Certificar constraints: email único, idade mínima 16, nome de curso único

 Adicionar relacionamentos se necessário

 Criar índices para consultas comuns (email, matrícula, cursos ativos)

 Gerar script SQL atualizado para referência/documentação

2️⃣ Backend (Spring Boot)

 Criar entidades JPA correspondentes às tabelas

 Configurar repositórios Spring Data JPA

 Criar serviços com regras de negócio:

 Registro de estudante (idade, email único)

 CRUD de cursos (validação de nome único, 6 meses)

 Matrícula (limite 3 cursos)

 Log de tarefas (mínimo 30min, múltiplos logs por dia)

 Criar controllers REST para cada funcionalidade

 Configurar JWT:

 Login / geração de token

 Filtro de autenticação nos endpoints

 Preparar event producers Kafka:

 StudentRegistered

 CourseCreated / CourseUpdated

 EnrollmentCreated

 TaskLogged

3️⃣ Kafka

 Instalar e configurar Kafka + Zookeeper (local ou Docker)

 Criar tópicos:

 EstudanteRegistrado

 CursoCriado

 CursoAtualizado

 MatriculaCriada

 TarefaRegistrada

 Testar produção e consumo com eventos simples

4️⃣ Docker

 Criar Dockerfile para backend

 Criar Dockerfile para frontend

 Configurar docker-compose com:

 Backend

 Frontend

 PostgreSQL

 Kafka + Zookeeper

 Testar se todos os containers sobem corretamente

5️⃣ Frontend (Angular)

 Criar telas básicas:

 Registro/Login

 Listagem de cursos

 Matrícula

 Log de tarefas

 Consumir API REST backend

 Implementar autenticação JWT no frontend

6️⃣ AWS (opcional / plus)

 Configurar S3 para backup de logs ou tarefas

 Configurar SES para envio de e-mails (boas-vindas, notificações)
