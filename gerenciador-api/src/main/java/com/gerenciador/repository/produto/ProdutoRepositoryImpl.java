package com.gerenciador.repository.produto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.gerenciador.model.Produto;
import com.gerenciador.model.Produto_;
import com.gerenciador.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Produto> filter(ProdutoFilter produtoFilter, Pageable pageAble) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		
		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		
		criteria.where(predicates);
		TypedQuery<Produto> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageAble);
		
		return new PageImpl<>(query.getResultList(), pageAble, total(produtoFilter));
	}
	
	
	


	private Predicate[] criarRestricoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(produtoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Produto_.NOME)), "%" + produtoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(produtoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Produto_.DESCRICAO)), "%" + produtoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(produtoFilter.getMarca())) {
			predicates.add(builder.like(
					builder.lower(root.get(Produto_.MARCA)), "%" + produtoFilter.getMarca().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(produtoFilter.getDistribuidor())) {
			predicates.add(builder.like(
					builder.lower(root.get(Produto_.distribuidor)), "%" + produtoFilter.getDistribuidor().toLowerCase() + "%"));
		}
		
//		if(!StringUtils.isEmpty(produtoFilter.getAnoDeFabricacao())) {
//			predicates.add(builder.like(
//					builder.lower(root.get("descricao")), "%" + produtoFilter.getNome().toLowerCase() + "%"));
//		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Produto> query, Pageable pageAble) {
		int paginaAtual = pageAble.getPageNumber();
		int totalRegistroPorPagina = pageAble.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		
	}
	
	private Long total(ProdutoFilter produtoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Produto> root = criteria.from(Produto.class);
		
		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
