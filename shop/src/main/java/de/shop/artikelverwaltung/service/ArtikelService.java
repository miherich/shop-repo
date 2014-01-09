package de.shop.artikelverwaltung.service;

import java.io.Serializable;
//import java.util.Collections;
import java.util.List;
//import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
//import javax.persistence.NamedQuery;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

//import com.google.common.base.Strings;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
//import de.shop.artikelverwaltung.domain.Fahrrad;
//import de.shop.artikelverwaltung.domain.Zubehoer;
//import de.shop.kundenverwaltung.domain.AbstractKunde;
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
	
	public <T extends AbstractArtikel> T createArtikel(T artikel){
		if(artikel==null)
			return artikel;
		
		em.persist(artikel);
		return artikel;
	}

//	
//	public void updateArtikel(AbstractArtikel artikel) {
//		 Mock.updateArtikel(artikel);
//	}
}
