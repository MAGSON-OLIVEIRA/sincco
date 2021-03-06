package com.sincco.repository.helper.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sincco.model.Produto;
import com.sincco.repository.filter.ProdutoFilter;
import com.sincco.repository.paginacao.PaginacaoUtil;

public class ProdutosImpl  implements ProdutosQueries{
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}


	private Long total(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		if(filtro != null){
			if (!StringUtils.isEmpty(filtro.getId())) {
				criteria.add(Restrictions.eq("id", filtro.getId()));
			}
			
			if(!StringUtils.isEmpty(filtro.getDescricao())){
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(filtro.getSiglaProduto())){
				criteria.add(Restrictions.ilike("siglaProduto", filtro.getSiglaProduto(), MatchMode.ANYWHERE));
			}
		}
		
	}


	

}
