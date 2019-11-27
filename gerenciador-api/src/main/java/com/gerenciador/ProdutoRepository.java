package com.gerenciador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}