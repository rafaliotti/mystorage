package com.gerenciador.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.erromessage.Erro;
import com.gerenciador.event.RecursoCriadoEvent;
import com.gerenciador.model.Produto;
import com.gerenciador.repository.ProdutoRepository;
import com.gerenciador.service.ProdutoInexistenteException;
import com.gerenciador.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	//descomentar posteriormente
	
//	@GetMapping
//	public ResponseEntity<List<Produto>> buscarProdutos(){
//		
//		List<Produto> produtos = this.produtoService.filtrarProdutos();
//		
//		return ResponseEntity.ok(produtos);
//	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> pesquisarProdutos(){
		
		List<Produto> produtos = this.produtoRepository.findAll();
		
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProdutoById(@PathVariable Long id){
		
		Produto produto = this.produtoService.buscarProduto(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> incluirProduto(@Valid @RequestBody Produto produto, HttpServletResponse response){
				
		Produto produtoSalvo = this.produtoRepository.save(produto);
		publish.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletarProdutoById(@PathVariable Long id){
		
		Optional<Produto> produto = this.produtoRepository.findById(id);
		
		if(produto.isEmpty() || produto == null) {
			throw new ProdutoInexistenteException();
		}
		
		this.produtoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Optional<Produto>> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto){
		
		Optional<Produto> produtoSalvo = this.produtoRepository.findById(id);
		
		BeanUtils.copyProperties(produto, produtoSalvo.get(), "id");
		this.produtoRepository.save(produtoSalvo.get());
		
		return ResponseEntity.ok(produtoSalvo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ExceptionHandler({ ProdutoInexistenteException.class })
	public ResponseEntity<Object> handlerProdutoInexistenteException(ProdutoInexistenteException ex){
		String mensagemUsuario = messages.getMessage("produto.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenv = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenv));
		
		return ResponseEntity.badRequest().body(erros);
	}

	public List<Erro> listaDeErros(BindingResult bindingResult){
		
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError erro : bindingResult.getFieldErrors()) {
			
			String mensagemUsuario = this.messages.getMessage(erro, LocaleContextHolder.getLocale());
			String mensagemDesenv = erro.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenv));
		}
		
		return erros;
	}

}
