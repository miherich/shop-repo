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

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.service.KundeService;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;

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
	
	private static final int MAX_BESTELLUNGEN = 10;
	
	/**
	 * {inheritDoc}
	 */
	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_ID_BV)
	public Bestellung findBestellungById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		
		Bestellung bestellung;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		bestellung = em.find(Bestellung.class, id);
		
		return bestellung;
	}
	
	@Override
	@Size(min = 1, message = "{bestellung.notFound.ids}")
	public List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		
		// SELECT b
		// FROM   Bestellung b
		// WHERE  b.id = <id> OR ...

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Bestellung> criteriaQuery  = builder.createQuery(Bestellung.class);
		final Root<Bestellung> b = criteriaQuery.from(Bestellung.class);
		
		// Die Vergleichen mit "=" als Liste aufbauen
		final Path<Long> idPath = b.get("id");
		final List<Predicate> predList = new ArrayList<>();
		for (Long id : ids) {
			final Predicate equal = builder.equal(idPath, id);
			predList.add(equal);
		}
		// Die Vergleiche mit "=" durch "or" verknuepfen
		final Predicate[] predArray = new Predicate[predList.size()];
		final Predicate pred = builder.or(predList.toArray(predArray));
		criteriaQuery.where(pred).distinct(true);

		final TypedQuery<Bestellung> query = em.createQuery(criteriaQuery);
		if (FetchType.MIT_LIEFERUNGEN.equals(fetch)) {
			final EntityGraph<?> entityGraph = em.getEntityGraph(Bestellung.GRAPH_LIEFERUNGEN);
			query.setHint(LOADGRAPH, entityGraph);
		}
				
		return query.getResultList();
	}


	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_ID_BV)
	public Position findPositionById(Long id, Long bid) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findPositionById(id, bid);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	@NotNull (message = BESTELLUNG_ZU_KUNDE_NOT_FOUND_BV)
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findBestellungenByKunde(kunde);
	}
	
	@Override
	@NotNull(message = BESTELLUNG_NOT_FOUND_BV)
	public List<Bestellung> findAllBestellungen() {
		final int anzahl = MAX_BESTELLUNGEN;
		final List<Bestellung> bestellungList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = Mock.findBestellungById(i);
			bestellungList.add(bestellung);
		}
		return bestellungList;
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
			kunde = ks.findKundeById(kunde.getId(), KundeService.FetchType.MIT_BESTELLUNGEN);
		}
		kunde.addBestellung(bestellung);
		bestellung.setKunde(kunde);
		
		// Vor dem Abspeichern IDs zuruecksetzen:
		// IDs koennten einen Wert != null haben, wenn sie durch einen Web Service uebertragen wurden
		bestellung.setId(KEINE_ID);
		for (Bestellposition bp : bestellung.getBestellpositionen()) {
			bp.setId(KEINE_ID);
		}
		// FIXME JDK 8 hat Lambda-Ausdruecke
		//bestellung.getBestellpositionen()
		//          .forEach(bp -> bp.setId(KEINE_ID));
		
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
