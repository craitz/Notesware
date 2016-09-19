package com.camilo.anotacoes.repository.helper.lembrete;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.camilo.anotacoes.model.Lembrete;
import com.camilo.anotacoes.repository.filter.LembreteFilter;

public class LembretesImpl implements LembretesQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Lembrete> filtrar(LembreteFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Lembrete.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual*totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		Sort sort = pageable.getSort();
		System.out.println(">>> sort: " + sort);
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(LembreteFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getTitulo())) {
				criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}

			if (isCategoriaPresente(filtro)) {
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria()));
			}

			if (!StringUtils.isEmpty(filtro.getGrupo())) {
				criteria.add(Restrictions.eq("grupo", filtro.getGrupo()));
			}

			if (!StringUtils.isEmpty(filtro.getDataCadastro())) {
				criteria.add(Restrictions.eq("dataCadastro", filtro.getDataCadastro()));
			}
		}
	}
	
	private boolean isCategoriaPresente(LembreteFilter filtro) {
		return filtro.getCategoria() != null && filtro.getCategoria().getCodigo() != null;
	}
	
	private Long total(LembreteFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Lembrete.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}

}
