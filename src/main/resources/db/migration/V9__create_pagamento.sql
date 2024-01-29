CREATE TABLE IF NOT EXISTS pagamento (
                                         id INTEGER PRIMARY KEY,
                                         pedido_id INTEGER NOT NULL,
                                         data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         status_pedido_id INTEGER NOT NULL
);

