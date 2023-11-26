create table restaurante_forma_pagamentos (

    restaurante_id int8 not null,
    pagamento_id int8 not null,

    FOREIGN KEY (restaurante_id) REFERENCES restaurante(id) ON DELETE CASCADE,
    FOREIGN KEY (pagamento_id) REFERENCES forma_pagamento(id),

    PRIMARY KEY(restaurante_id, pagamento_id)
);