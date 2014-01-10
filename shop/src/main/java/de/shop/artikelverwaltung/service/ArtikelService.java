package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
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
	
	@NotNull(message = "{artikel.notFound.id}")
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
}
