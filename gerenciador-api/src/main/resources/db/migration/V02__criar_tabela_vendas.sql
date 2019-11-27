CREATE TABLE vendas(
	id BigInt AUTO_INCREMENT PRIMARY KEY NOT NULL,
	descricao Varchar(150) NOT NULL,
	valorTotal Double(10,2) NOT NULL,
	data Date NOT NULL
)Engine = InnoDB;