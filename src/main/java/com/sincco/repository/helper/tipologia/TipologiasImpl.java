package com.sincco.repository.helper.tipologia;

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

import com.sincco.model.Tipologia;
import com.sincco.repository.filter.TipologiaFilter;
import com.sincco.repository.paginacao.PaginacaoUtil;

public class TipologiasImpl implements TipologiasQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public Page<Tipologia> filtrar(TipologiaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Tipologia.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}


	private Long total(TipologiaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Tipologia.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(TipologiaFilter filtro, Criteria criteria) {
		criteria.createAlias("leitura", "l");
		criteria.createAlias("moradia", "m");
		
		if(filtro!= null){
				
			if(!StringUtils.isEmpty(filtro.getId())){
				criteria.add(Restrictions.eqOrIsNull("id", filtro.getId()));
			}
			
			if(!StringUtils.isEmpty(filtro.getDescricao())){
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(),MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getSigla())){
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(),MatchMode.ANYWHERE));
			}
			
			
			if (!StringUtils.isEmpty(filtro.getDescricaoLeitura())) {
				criteria.add(Restrictions.ilike("l.descricao", filtro.getDescricaoLeitura(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getDescricaoMoradia())) {
				criteria.add(Restrictions.ilike("m.descricao", filtro.getDescricaoMoradia(), MatchMode.ANYWHERE));
			}
			
		}
		
	}

}
