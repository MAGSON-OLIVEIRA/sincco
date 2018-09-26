package com.sincco.repository.helper.cepServico;

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

import com.sincco.model.CepServico;
import com.sincco.repository.filter.CepServicoFilter;
import com.sincco.repository.paginacao.PaginacaoUtil;

public class CepsServicosImpl implements CepsServicosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<CepServico> filtrar(CepServicoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(CepServico.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(CepServicoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(CepServico.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(CepServicoFilter filtro, Criteria criteria) {
	
		if(filtro != null){
			if (!StringUtils.isEmpty(filtro.getId())) {
				criteria.add(Restrictions.eq("id", filtro.getId()));
			}
			
			if(!StringUtils.isEmpty(filtro.getCep())){
				criteria.add(Restrictions.ilike("cep", filtro.getCep(), MatchMode.ANYWHERE));
			}

		}
		
	}

}
