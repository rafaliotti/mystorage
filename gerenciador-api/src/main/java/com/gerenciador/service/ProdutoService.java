package com.gerenciador.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gerenciador.ProdutoTipo;
import com.gerenciador.model.Produto;
import com.gerenciador.repository.ProdutoRepository;
import com.gerenciador.repository.filter.ProdutoFilter;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
//	public Optional<Produto> filtrarProdutos(ProdutoFilter filter){
//		
//		if(ProdutoTipo.PRIMEIRO.toString().equals("PRIMEIRO")) {
//			System.out.println("TRUE");
//		}
//		
//		
//		
//		
//		return null;
//	}
	
	public Produto buscarProduto(Long id){
		
		Optional<Produto> produtoSalvo = this.produtoRepository.findById(id);
		
			if(produtoSalvo.isEmpty() || produtoSalvo.get() == null) {
				throw new EmptyResultDataAccessException(1);
			}
		
		return produtoSalvo.get();
	}
	
}
