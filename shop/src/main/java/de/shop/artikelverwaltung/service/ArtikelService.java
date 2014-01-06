package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;

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

import com.google.common.base.Strings;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.util.interceptor.Log;
import de.shop.util.Mock;

@Log
@Dependent
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;
	
	public static final String ARTIKEL_NOT_FOUND_BV = "{artikelverwaltung.notFound.all}";
	public static final String ARTIKEL_NOT_FOUND_ID_BV = "{artikelverwaltung.artikel.notFound.id}";
	
	@Inject
	private transient EntityManager em;
	
	/**
	 * Suche nach verfuegbaren Artikeln.
	 * @return Liste der verfuegbaren Artikel.
	 */
	public List<AbstractArtikel> findVerfuegbareArtikel() {
		return em.createNamedQuery(AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL, AbstractArtikel.class)
				 .getResultList();
	}

	
	/**
	 * Suche den Artikel zu gegebener ID.
	 * @param id ID des gesuchten Artikels.
	 * @return Der gefundene Artikel, null sonst.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Artikel gefunden wurde
	 */
	@NotNull(message = "{artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		return em.find(AbstractArtikel.class, id);
	}
	
	/**
	 * Suche die Artikel zu gegebenen IDs. 
	 * @param ids Liste der IDs
	 * @return Liste der gefundenen Artikel
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{Artikelverwaltung.artikel.notFound.ids}")
	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		
		/*
		 * SELECT a
		 * FROM   Artikel a
		 * WHERE  a.id = ? OR a.id = ? OR ...
		 */
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder.createQuery(AbstractArtikel.class);
		final Root<AbstractArtikel> a = criteriaQuery.from(AbstractArtikel.class);

		final Path<Long> idPath = a.get("id");
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

	
	/**
	 * Suche die Artikel mit gleicher Bezeichnung
	 * @param bezeichnung Gemeinsame Bezeichnung der gesuchten Artikel
	 * @return Liste der gefundenen Artikel
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{artikel.notFound.bezeichnung}")
	public List<AbstractArtikel> findArtikelByBezeichnung(String bezeichnung) {
		if (Strings.isNullOrEmpty(bezeichnung)) {
			return findVerfuegbareArtikel();
		}
		
		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_BEZ, AbstractArtikel.class)
				 .setParameter(AbstractArtikel.PARAM_BEZEICHNUNG, "%" + bezeichnung + "%")
				 .getResultList();
	}
	
	/**
	 * Suche Artikel, die preiswerter als ein bestimmter Preis sind
	 * @param preis Maximaler Preis
	 * @return Liste der Artikel mit einem geringeren Preis als die angegebene Obergrenze
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.maxPreis}")
	public List<AbstractArtikel> findArtikelByMaxPreis(BigDecimal preis) {
		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_MAX_PREIS, AbstractArtikel.class)
				 .setParameter(AbstractArtikel.PARAM_PREIS, preis)
				 .getResultList();
	}
//	
//	@NotNull(message = ARTIKEL_NOT_FOUND_ID_BV)
//	public AbstractArtikel findArtikelById(Long id) {
//		// TODO id pruefen
//		// TODO Datenbanzugriffsschicht statt Mock
//		return Mock.findArtikelById(id);
//	}
//
//	@NotNull(message = ARTIKEL_NOT_FOUND_BV)
//	public List<AbstractArtikel> findAllArtikel() {
//		return Mock.findAllArtikel();
//	}
//	
//	public Fahrrad createFahrrad(Fahrrad fahrrad) {
//		return Mock.createFahrrad(fahrrad);
//	}
//	
//	public Zubehoer createZubehoer(Zubehoer zubehoer) {
//		return Mock.createZubehoer(zubehoer);
//	}
//
//	
//	public void updateArtikel(AbstractArtikel artikel) {
//		 Mock.updateArtikel(artikel);
//	}
}
