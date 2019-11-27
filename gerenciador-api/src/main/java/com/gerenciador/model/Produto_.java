package com.gerenciador.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> marca;
	public static volatile SingularAttribute<Produto, Integer> tipo;
	public static volatile SingularAttribute<Produto, Date> dataValidade;
	public static volatile SingularAttribute<Produto, Date> dataRegistro;
	public static volatile SingularAttribute<Produto, String> lote;
	public static volatile SingularAttribute<Produto, Date> dataFabricacao;
	public static volatile SingularAttribute<Produto, Double> valor;
	public static volatile SingularAttribute<Produto, Boolean> disponivel;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> distribuidor;
	public static volatile SingularAttribute<Produto, String> descricao;

	public static final String MARCA = "marca";
	public static final String TIPO = "tipo";
	public static final String DATA_VALIDADE = "dataValidade";
	public static final String DATA_REGISTRO = "dataRegistro";
	public static final String LOTE = "lote";
	public static final String DATA_FABRICACAO = "dataFabricacao";
	public static final String VALOR = "valor";
	public static final String DISPONIVEL = "disponivel";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DISTRIBUIDOR = "distribuidor";
	public static final String DESCRICAO = "descricao";

}

