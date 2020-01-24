package com.gerenciador.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gerenciador.model.Produto;
import com.gerenciador.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	
	public Page<Produto> filter(ProdutoFilter produtoFilter, Pageable pageAble);

}
