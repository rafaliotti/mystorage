package com.gerenciador.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

public class ProdutoFilter {

	private String nome;
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String anoDeFabricacao;
	
	
	private String marca;
	
	private String distribuidor;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAnoDeFabricacao() {
		return anoDeFabricacao;
	}
	public void setAnoDeFabricacao(String anoDeFabricacao) {
		this.anoDeFabricacao = anoDeFabricacao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
	
	
	
	
}
