ALTER TABLE pedido ADD codigo VARCHAR(36) NOT NULL after id;

update pedido set codigo = uuid();

ALTER TABLE pedido ADD CONSTRAINT uk_pedido_codigp UNIQUE (codigo);