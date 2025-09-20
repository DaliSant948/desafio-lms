# Desafio LMS - Checklist de Desenvolvimento

## 1️⃣ Banco de Dados
- [X] Revisar tabelas criadas: Pessoa, Curso, Matricula, Tarefa, Categoria
- [X] Certificar constraints: email único, idade mínima 16, nome de curso único
- [ ] Adicionar relacionamentos se necessário
- [X] Criar índices para consultas comuns (email, matrícula, cursos ativos)
- [X] Gerar script SQL atualizado para referência/documentação

## 2️⃣ Backend (Spring Boot)
- [X] Criar entidades JPA correspondentes às tabelas
- [X] Configurar repositórios Spring Data JPA
- [X] Criar serviços com regras de negócio:
    - [X] Registro de estudante (idade, email único)
    - [X] CRUD de cursos (validação de nome único, 6 meses)
    - [X] Matrícula (limite 3 cursos)
    - [X] Log de tarefas (mínimo 30min, múltiplos logs por dia)
- [X] Criar controllers REST para cada funcionalidade
- [ ] Configurar JWT:
    - [ ] Login / geração de token
    - [ ] Filtro de autenticação nos endpoints
- [ ] Preparar event producers Kafka:
    - [ ] StudentRegistered
    - [ ] CourseCreated / CourseUpdated
    - [ ] EnrollmentCreated
    - [ ] TaskLogged

  ## 3️⃣ Kafka
- [ ] Instalar e configurar Kafka + Zookeeper (local ou Docker)
- [ ] Criar tópicos:
    - [ ] EstudanteRegistrado
    - [ ] CursoCriado
    - [ ] CursoAtualizado
    - [ ] MatriculaCriada
    - [ ] TarefaRegistrada
- [ ] Testar produção e consumo com eventos simples

## 4️⃣ Docker
- [ ] Criar Dockerfile para backend
- [ ] Criar Dockerfile para frontend
- [ ] Configurar docker-compose com:
    - [ ] Backend
    - [ ] Frontend
    - [ ] PostgreSQL
    - [ ] Kafka + Zookeeper
- [ ] Testar se todos os containers sobem corretamente

## 5️⃣ Frontend (Angular)
- [ ] Criar telas básicas:
    - [ ] Registro/Login
    - [ ] Listagem de cursos
    - [ ] Matrícula
    - [ ] Log de tarefas
- [ ] Consumir API REST backend
- [ ] Implementar autenticação JWT no frontend

## 6️⃣ AWS (opcional / plus)
- [ ] Configurar S3 para backup de logs ou tarefas
- [ ] Configurar SES para envio de e-mails (boas-vindas, notificaç
