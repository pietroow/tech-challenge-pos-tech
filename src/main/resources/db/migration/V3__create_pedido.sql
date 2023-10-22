CREATE TABLE IF NOT EXISTS pedido (
                                      id INTEGER PRIMARY KEY,
                                      produtos VARCHAR(255) NOT NULL,
                                      cliente_id INTEGER,
                                      data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (cliente_id) REFERENCES Cliente (id)
);
