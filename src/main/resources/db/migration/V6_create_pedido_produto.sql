CREATE TABLE IF NOT EXISTS pedido_produto (
                                              pedido_id INTEGER,
                                              produto_id INTEGER,
                                              quantidade INTEGER,
                                              FOREIGN KEY (pedido_id) REFERENCES pedido (id),
                                              FOREIGN KEY (produto_id) REFERENCES produto (id)
);