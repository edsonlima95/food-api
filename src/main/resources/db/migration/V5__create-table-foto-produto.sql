create table foto_produto (

    produto_id int8 PRIMARY KEY NOT NULL,
    nome_arquivo VARCHAR(150) NOT NULL,
    descricao VARCHAR(150),
    content_type VARCHAR(80) NOT NULL,
    tamanho int4 NOT NULL,

    FOREIGN KEY (produto_id) REFERENCES produto(id)
)