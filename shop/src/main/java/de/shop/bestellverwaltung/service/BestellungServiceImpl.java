package de.shop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	private static final long serialVersionUID = -519454062519816252L;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;
	
	private static final int MAX_BESTELLUNGEN = 10;
	
	/**
	 * {inheritDoc}
	 */
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(int id) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findBestellungById(id);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findBestellungenByKunde(kunde);
	}
	
	@Override
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
	public Bestellung createBestellung(Bestellung bestellung) {
		// TODO Datenbanzugriffsschicht statt Mock
		bestellung = Mock.createBestellung(bestellung);
		event.fire(bestellung);
		
		return bestellung;
	}
}
