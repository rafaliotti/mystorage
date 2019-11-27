package com.gerenciador.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gerenciador.ProdutoRepository;
import com.gerenciador.model.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscarProdutos(){
		
		List<Produto> produtos = this.produtoRepository.findAll();
		
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> buscarProdutoById(@Valid @PathVariable Long id){
		
		Optional<Produto> produto = this.produtoRepository.findById(id);
		
		
		return ResponseEntity.ok(produto);
	}
	
	
	@PostMapping
	public void incluirProduto(@Valid @RequestBody HttpServletResponse response, Produto produto){
				
		this.produtoRepository.save(produto);
		
		Long id =  produto.getId();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		
	}

}
