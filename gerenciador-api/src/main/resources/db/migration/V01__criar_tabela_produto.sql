CREATE TABLE produto(
	id BigInt AUTO_INCREMENT PRIMARY KEY NOT NULL,
	nome Varchar(50) NOT NULL,
	descricao Varchar(100) NOT NULL,
	tipo int(5) NOT NULL,
	valor Double(10,2) NOT NULL,
	disponivel Boolean,
	marca Varchar(20) NOT NULL,
	distribuidor Varchar(150),
	lote Varchar(50),
	dataFabricacao Date NOT NULL,
	dataValidade Date NOT NULL,
	dataRegistro Date NOT NULL
)Engine = InnoDB;