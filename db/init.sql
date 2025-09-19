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
    email VARCHAR(255) NOT NULL UNIQUE, -- Garante que cada e-mail seja único
    telefone VARCHAR(50),
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('estudante', 'administrador')),
    ultimo_login TIMESTAMP WITH TIME ZONE,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- ======================
-- TABELA: cursos
-- ======================
CREATE TABLE cursos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL UNIQUE, -- Nome do curso deve ser único
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    ativo BOOLEAN DEFAULT true,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CHECK (data_fim <= data_inicio + INTERVAL '6 months') -- Curso deve durar no máximo 6 meses
);

-- ======================
-- TABELA: categorias
-- ======================
CREATE TABLE categorias (
    id SMALLINT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(200) NOT NULL
);

-- Inserindo valores iniciais
INSERT INTO categorias (id, codigo, descricao) VALUES
(1, 'PESQUISA', 'Pesquisa'),
(2
