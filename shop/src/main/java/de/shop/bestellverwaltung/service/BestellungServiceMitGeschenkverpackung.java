package de.shop.bestellverwaltung.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;

@Decorator
@Dependent
public abstract class BestellungServiceMitGeschenkverpackung implements BestellungService {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	@Delegate
	//@Any
	private BestellungService bs;

	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung findBestellungById(int id) {
		return bs.findBestellungById(id);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		return bs.findBestellungenByKunde(kunde);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public Bestellung createBestellung(Bestellung bestellung) {
		LOGGER.warn("Geschenkverpackung noch nicht implementiert");
		
		return bs.createBestellung(bestellung);
	}
}
