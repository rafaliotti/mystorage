package com.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.model.Produto;
import com.gerenciador.repository.produto.ProdutoRepositoryQuery;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery{

}