CREATE TABLE IF NOT EXISTS pedido (
                        id SERIAL PRIMARY KEY,
                        produtos JSONB NOT NULL,
                        cliente_id INTEGER,
                        data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (cliente_id) REFERENCES Cliente (id)
);
