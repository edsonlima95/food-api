create table pedido (
  id bigserial PRIMARY KEY NOT NULL,
  subtotal decimal(10,2) NOT NULL,
  taxa_frete decimal(10,2) NOT NULL,
  valor_total decimal(10,2) NOT NULL,

  restaurante_id int8 NOT NULL,
  usuario_cliente_id int8 NOT NULL,
  forma_pagamento_id int8 NOT NULL,

  endereco_cidade_id int8 NOT NULL,
  endereco_cep varchar(9) NOT NULL,
  endereco_logradouro varchar(100) NOT NULL,
  endereco_numero varchar(20) NOT NULL,
  endereco_complemento varchar(60) null,
  endereco_bairro varchar(60) NOT NULL,

  status varchar(10) NOT NULL,
  data_criacao timestamp NOT NULL,
  data_confirmacao timestamp null,
  data_cancelamento timestamp null,
  data_entrega timestamp null,

  foreign key (restaurante_id) references restaurante (id),
  foreign key (usuario_cliente_id) references usuario (id),
  foreign key (forma_pagamento_id) references forma_pagamento (id)
);

create table item_pedido (

  id bigserial PRIMARY KEY NOT NULL,
  quantidade int4 NOT NULL,
  preco_unitario decimal(10,2) NOT NULL,
  preco_total decimal(10,2) NOT NULL,
  observacao varchar(255) null,
  pedido_id int8 NOT NULL,
  produto_id int8 NOT NULL,

  foreign key (pedido_id) references pedido (id) ON DELETE CASCADE,
  foreign key (produto_id) references produto (id)
);