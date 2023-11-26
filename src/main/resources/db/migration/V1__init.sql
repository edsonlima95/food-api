CREATE TABLE cozinha (
	id bigserial PRIMARY KEY NOT NULL,
	nome varchar(50) NOT NULL
);

CREATE TABLE estado (
	id bigserial PRIMARY KEY NOT NULL ,
	nome varchar(100) NOT NULL
);

CREATE TABLE cidade (
	id bigserial PRIMARY KEY NOT NULL,
	nome varchar(100) NOT NULL,
	estado_id int8 NULL,
	FOREIGN KEY (estado_id) REFERENCES estado(id)
);

CREATE TABLE permissao (
	id bigserial PRIMARY KEY NOT NULL,
	descricao varchar(100) NULL,
	nome varchar(50) NOT NULL
);

CREATE TABLE grupo (
	id bigserial PRIMARY KEY NOT NULL,
	nome varchar(100) NOT NULL
);

CREATE TABLE grupo_permissoes (
	grupo_id int8 NOT NULL,
	permissao_id int8 NOT NULL,

	FOREIGN KEY (permissao_id) REFERENCES permissao(id),
	FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON DELETE CASCADE,

	primary key (grupo_id, permissao_id)
);

CREATE TABLE usuario (
	id bigserial PRIMARY KEY NOT NULL,
	data_criacao timestamp(6) NOT NULL,
	email varchar(100) NOT NULL,
	nome varchar(100) NOT NULL,
	senha varchar(200) NOT NULL
);

CREATE TABLE usuario_grupos (

	usuario_id int8 NOT NULL,
	grupo_id int8 NOT NULL,

	FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
	FOREIGN KEY (grupo_id) REFERENCES grupo(id),

	primary key (usuario_id, grupo_id)
);


CREATE TABLE forma_pagamento (
	id bigserial PRIMARY KEY NOT NULL,
	descricao varchar(100) NOT NULL
);


CREATE TABLE restaurante (
	id bigserial PRIMARY KEY NOT NULL,
	data_atualizacao timestamp(6) NOT NULL,
	data_criacao timestamp(6) NOT NULL,
	endereco_bairro varchar(255) NULL,
	endereco_cep varchar(255) NULL,
	endereco_complemento varchar(255) NULL,
	endereco_logradouro varchar(255) NULL,
	endereco_numero varchar(255) NULL,
	nome varchar(100) NOT NULL,
	taxa_frete numeric(10, 2) NULL,
	cozinha_id int8 NULL,
	endereco_cidade_id int8 NULL,
	ativo bool NOT NULL DEFAULT TRUE,
	FOREIGN KEY (cozinha_id) REFERENCES cozinha(id),
	FOREIGN KEY (endereco_cidade_id) REFERENCES cidade(id)
);


CREATE TABLE produto (
	id bigserial PRIMARY KEY NOT NULL,
	ativo bool NOT NULL,
	descricao varchar(200) NULL,
	nome varchar(100) NOT NULL,
	preco varchar(255) NOT NULL,
	restaurante_id int8 NULL,
	FOREIGN KEY (restaurante_id) REFERENCES restaurante(id) ON DELETE CASCADE
);
