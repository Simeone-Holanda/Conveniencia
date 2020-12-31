
USE login;
CREATE TABLE vendas(
	codProduct  int(4)  AUTO_INCREMENT ,
    descricao varchar(30) NOT NULL,
    preco float NOT NULL,
    PRIMARY KEY(codProduct)
);
/*caso queira inserir mais algum elemento é so fzr o msm*/
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Cx itaipava",38); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni itaipava",3.5); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Cx petra",36); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni petra",3.0); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Cx local",33); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni local",3.0); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Cx bramaD.M.",45); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni bramaD.M.",4.0); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni budweiser",5.0); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni heineken",6.0); 
INSERT INTO produtos(codProduct, descricao, preco) VALUES (null, "Uni corona",7.0); 
