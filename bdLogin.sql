create DATABASE login;
USE login;
CREATE TABLE usuarios(
	id 	int(4) AUTO_INCREMENT,
    nome varchar(30) NOT NULL,
    senha varchar(50) NOT NULL,
    PRIMARY KEY(id)
);
INSERT INTO usuarios(id, nome, senha) VALUES (null, "Ricos","0112358"); /*caso queira inserir mais algum elemento é so fzr o msm*/
