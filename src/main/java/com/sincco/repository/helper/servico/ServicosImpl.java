package com.sincco.repository.helper.servico;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

import com.sincco.model.Servico;
import com.sincco.repository.filter.ServicoFilter;
import com.sincco.repository.paginacao.PaginacaoUtil;
import com.sincco.security.UsuarioSistema;


public class ServicosImpl implements ServicosQueries {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Servico> filtrar(ServicoFilter filtro, Pageable pageable, UsuarioSistema us) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Servico.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria, us);
		return new PageImpl<>(criteria.list(), pageable, total(filtro, us));
	}
	
	private Long total(ServicoFilter filtro, UsuarioSistema us) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Servico.class);
		adicionarFiltro(filtro, criteria, us );
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(ServicoFilter filtro, Criteria criteria, UsuarioSistema us) {
		if(us != null){
			criteria.createAlias("usuario", "u").add(Restrictions.eq("u.nome", us.getUsuario().getNome()));
		} else{
			criteria.createAlias("usuario", "u");
		}
		
		criteria.createAlias("produto", "p");
		criteria.createAlias("tipologia", "t");
		criteria.createAlias("cepServico", "c");
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getId())) {
				criteria.add(Restrictions.eq("id", filtro.getId()));
			}

            if (filtro.getAtivo() != null) {
                criteria.add(Restrictions.eq("ativo", filtro.getAtivo()));
            }
			
			if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataInicio", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("dataInicio", ate));
			}

			if (!StringUtils.isEmpty(filtro.getNomeUsuario())) {
				criteria.add(Restrictions.ilike("u.nome", filtro.getNomeUsuario(), MatchMode.ANYWHERE));
			}
			

			if (!StringUtils.isEmpty(filtro.getSiglaProduto())) {
				criteria.add(Restrictions.ilike("p.siglaProduto", filtro.getSiglaProduto(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getSiglaTipologia())) {
				criteria.add(Restrictions.ilike("t.sigla", filtro.getSiglaTipologia(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getCep())) {
				criteria.add(Restrictions.ilike("c.cep", filtro.getCep(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getEmailUsuario())) {
				criteria.add(Restrictions.ilike("u.email", filtro.getEmailUsuario(), MatchMode.ANYWHERE));
			}

		}
	}


}
