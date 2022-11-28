INSERT INTO cozinha (id,nome) values (1,'Pizzaria');
INSERT INTO cozinha (id, nome) values (2,'Lanche');


INSERT INTO restaurante (nome,taxa_entrega,cozinha_id) values ('Fornello' , 15.5,1);
INSERT INTO restaurante (nome,taxa_entrega, cozinha_id) values ('Fellipis' , 20,2);

INSERT INTO estado (id,nome) values (1,'Rio Grande do Sul');
INSERT INTO estado (id,nome) values (2,'Pernambuco');

INSERT INTO cidade(id,nome,estado_id) values (1,"Caxias do Sul",1);
INSERT INTO cidade(id,nome,estado_id) values (2,"Recife",2)