package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.interceptor.Log;

@Log
@Dependent
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;
	
	public static final String ARTIKEL_NOT_FOUND_BV = "{artikelverwaltung.notFound.all}";
	public static final String ARTIKEL_NOT_FOUND_ID_BV = "{artikelverwaltung.artikel.notFound.id}";
	
	@Inject
	private transient EntityManager em;
	
	@NotNull(message = ARTIKEL_NOT_FOUND_ID_BV)
	public AbstractArtikel findArtikelById(Long id) {
		return em.find(AbstractArtikel.class, id);
	}

	@NotNull(message = ARTIKEL_NOT_FOUND_BV)
	public List<AbstractArtikel> findAllArtikel() {
		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL, AbstractArtikel.class).getResultList();
	}
	
	public <T extends AbstractArtikel> T createArtikel(T artikel) {
		if (artikel == null)
			return artikel;
		
		em.persist(artikel);
		return artikel;
	}

	
	public <T extends AbstractArtikel> T updateArtikel(T artikel) {
		
		if (artikel == null) {
			return null;
		}
		
		em.merge(artikel);
		return artikel;
	}
	
	@Size(min = 1, message = "{artikel.notFound.ids}")
	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			final List<AbstractArtikel> leereListe = new ArrayList<>();
			return leereListe;
		}
		
		/*
		 * SELECT a
		 * FROM   Artikel a
		 * WHERE  a.id = ? OR a.id = ? OR ...
		 */
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder.createQuery(AbstractArtikel.class);
		final Root<AbstractArtikel> a = criteriaQuery.from(AbstractArtikel.class);

		final Path<Long> idPath = a.get("artikelNr");
		//final Path<String> idPath = a.get(Artikel_.id);   // Metamodel-Klassen funktionieren nicht mit Eclipse
		
		Predicate pred = null;
		if (ids.size() == 1) {
			// Genau 1 id: kein OR notwendig
			pred = builder.equal(idPath, ids.get(0));
		}
		else {
			// Mind. 2x id, durch OR verknuepft
			final Predicate[] equals = new Predicate[ids.size()];
			int i = 0;
			for (Long id : ids) {
				equals[i++] = builder.equal(idPath, id);
			}
			
			pred = builder.or(equals);
		}
		
		criteriaQuery.where(pred);
		
		return em.createQuery(criteriaQuery)
		         .getResultList();
	}
}
