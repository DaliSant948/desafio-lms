# Desafio LMS - Checklist de Desenvolvimento

## 1️⃣ Banco de Dados
- [ ] Revisar tabelas criadas: Pessoa, Curso, Matricula, Tarefa, Categoria
- [ ] Certificar constraints: email único, idade mínima 16, nome de curso único
- [ ] Adicionar relacionamentos se necessário
- [ ] Criar índices para consultas comuns (email, matrícula, cursos ativos)
- [ ] Gerar script SQL atualizado para referência/documentação

## 2️⃣ Backend (Spring Boot)
- [ ] Criar entidades JPA correspondentes às tabelas
- [ ] Configurar repositórios Spring Data JPA
- [ ] Criar serviços com regras de negócio:
    - [ ] Registro de estudante (idade, email único)
    - [ ] CRUD de cursos (validação de nome único, 6 meses)
    - [ ] Matrícula (limite 3 cursos)
    - [ ] Log de tarefas (mínimo 30min, múltiplos logs por dia)
- [ ] Criar controllers REST para cada funcionalidade
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
