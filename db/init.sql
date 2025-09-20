-- Criação do banco de dados LMS
-- Este script será executado automaticamente pelo Postgres no container

-- ======================
-- TABELA: pessoas
-- ======================
CREATE TABLE pessoas (
    id BIGSERIAL PRIMARY KEY,
    primeiro_nome VARCHAR(100) NOT NULL,
    ultimo_nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(50),
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('estudante', 'administrador')),
    ultimo_login TIMESTAMP WITH TIME ZONE,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT now()
    CONSTRAINT idade_minima CHECK (data_nascimento <= CURRENT_DATE - INTERVAL '16 years')
);

-- Índices para consultas comuns
CREATE INDEX idx_pessoas_email ON pessoas(email);

-- ======================
-- TABELA: cursos
-- ======================
CREATE TABLE cursos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL UNIQUE,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    ativo BOOLEAN DEFAULT true,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CHECK (data_fim <= data_inicio + INTERVAL '6 months')
);

-- Índice para cursos ativos
CREATE INDEX idx_cursos_ativo ON cursos(ativo);

-- ======================
-- TABELA: categorias
-- ======================
CREATE TABLE categorias (
    id SMALLINT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(200) NOT NULL
);

-- Inserindo valores iniciais em categorias
INSERT INTO categorias (id, codigo, descricao) VALUES
(1, 'PESQUISA', 'Pesquisa'),
(2, 'LEITURA', 'Leitura de materiais'),
(3, 'VIDEOAULA', 'Assistir videoaulas'),
(4, 'EXERCICIO', 'Resolução de exercícios'),
(5, 'PROJETO', 'Desenvolvimento de projetos');

-- ======================
-- TABELA: matriculas
-- ======================
CREATE TABLE matriculas (
 id BIGSERIAL PRIMARY KEY,
 pessoa_id BIGINT NOT NULL REFERENCES pessoas(id) ON DELETE CASCADE,
 curso_id BIGINT NOT NULL REFERENCES cursos(id) ON DELETE CASCADE,
 data_matricula TIMESTAMP WITH TIME ZONE DEFAULT now(),
status VARCHAR(20) NOT NULL CHECK (status IN ('ativo', 'concluido', 'cancelado')),
UNIQUE (pessoa_id, curso_id)
);

-- Índices para consultas comuns
CREATE INDEX idx_matriculas_pessoa_id ON matriculas(pessoa_id);
CREATE INDEX idx_matriculas_curso_id ON matriculas(curso_id);

-- ======================
-- TABELA: tarefas
-- ======================
CREATE TABLE tarefas (
id BIGSERIAL PRIMARY KEY,
matricula_id BIGINT NOT NULL REFERENCES matriculas(id) ON DELETE CASCADE,
categoria_id SMALLINT NOT NULL REFERENCES categorias(id),
descricao TEXT NOT NULL,
data_execucao TIMESTAMP WITH TIME ZONE DEFAULT now(),
duracao_minutos INT CHECK (duracao_minutos > 0)
);

-- ======================
-- DADOS DE EXEMPLO
-- ======================

-- Pessoas (admin e estudante)
INSERT INTO pessoas (primeiro_nome, ultimo_nome, data_nascimento, email, telefone, senha_hash, perfil)
VALUES
('Admin', 'LMS', '1990-01-01', 'admin@lms.com', '11999999999', 'admin123', 'administrador'),
('João', 'Silva', '2000-05-10', 'joao@lms.com', '11988888888', 'estudante123', 'estudante');

-- Curso exemplo
INSERT INTO cursos (nome, descricao, data_inicio, data_fim)
VALUES
('Java Backend com Spring', 'Curso intensivo de backend usando Spring Boot e Docker.', '2025-09-01', '2025-12-01');

-- Matrícula do João no curso
INSERT INTO matriculas (pessoa_id, curso_id, status)
VALUES
(2, 1, 'ativo');

-- Tarefa exemplo
INSERT INTO tarefas (matricula_id, categoria_id, descricao, duracao_minutos)
VALUES
(1, 2, 'Leitura do material de introdução ao Spring Boot', 45);
