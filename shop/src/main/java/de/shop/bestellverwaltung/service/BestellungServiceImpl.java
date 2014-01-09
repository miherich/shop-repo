package de.shop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.service.KundeService;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;
import static de.shop.util.Constants.KEINE_ID;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	private static final long serialVersionUID = -519454062519816252L;
	
	public static final String BESTELLUNG_NOT_FOUND_BV = "{bestellverwaltung.bestellung.notFound.all}";
	public static final String BESTELLUNG_NOT_FOUND_ID_BV = "{bestellverwaltung.bestellung.notFound.id}";
	public static final String BESTELLUNG_ZU_KUNDE_NOT_FOUND_BV = "{bestellverwaltung.bestellung.notFound.kunde}";
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;
	
	@Inject
	private transient EntityManager em;
	
	@Inject
	private KundeService ks;

	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_ID_BV)
	public Bestellung findBestellungById(Long id) {
		if (id == null) {
			return null;
		}
		
		Bestellung bestellung;
//		EntityGraph<?> entityGraph;
//		Map<String, Object> props;
		bestellung = em.find(Bestellung.class, id);
		
		return bestellung;
	}
	
	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_ID_BV)
	public Position findPositionById(Long id, Long bid) {
		return em.createNamedQuery(Position.FIND_POSITION_BY_ID, Position.class)
				.setParameter(Position.PARAM_POSITION_ID, id)
				.setParameter(Position.PARAM_BESTELLUNG_ID, bid)
				.getSingleResult();
	}
	
	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_BV)
	public List<Bestellung> findAllBestellungen() {
		return em.createNamedQuery(Bestellung.FIND_BESTELLUNGEN, Bestellung.class)
				.getResultList();
	}
	


	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		if (bestellung == null) {
			return null;
		}
		
		// Den persistenten Kunden mit der transienten Bestellung verknuepfen
		final AbstractKunde kunde = ks.findKundeById(kundeId, KundeService.FetchType.MIT_BESTELLUNGEN);
		return createBestellung(bestellung, kunde);
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde) {
		if (bestellung == null) {
			return null;
		}
		
		// Den persistenten Kunden mit der transienten Bestellung verknuepfen
		if (!em.contains(kunde)) {
			kunde = ks.findKundeById(kunde.getKundennr(), KundeService.FetchType.MIT_BESTELLUNGEN);
		}
		kunde.addBestellung(bestellung);
		bestellung.setKunde(kunde);
		
		// Vor dem Abspeichern IDs zuruecksetzen:
		// IDs koennten einen Wert != null haben, wenn sie durch einen Web Service uebertragen wurden
		bestellung.setBestellnr(KEINE_ID);
		for (Position bp : bestellung.getPositionen()) {
			bp.setId(KEINE_ID);
		}

		em.persist(bestellung);
		event.fire(bestellung);

		return bestellung;
	}

//	@Override
//	public Position createPosition(Position position)
//	{
//		position = Mock.createPosition(position);
//		
//		return position;
//	}

}
