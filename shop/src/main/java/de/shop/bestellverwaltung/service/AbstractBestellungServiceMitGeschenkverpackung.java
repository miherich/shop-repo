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
import de.shop.bestellverwaltung.domain.Position;
import de.shop.util.interceptor.Log;

@Decorator
@Dependent
@Log
public abstract class AbstractBestellungServiceMitGeschenkverpackung implements BestellungService {
	
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

	@Override
	public Position findPositionById(int id, int bid) {
		return bs.findPositionById(id, bid);
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
		
		final Bestellung bestellung1 = bs.createBestellung(bestellung);
		
		bestellung1.setMitVerpackung(true);
		
		LOGGER.infof("Jetzt ist ne Geschenkverpackung um die Bestellung %s rum", bestellung);
		
		return bestellung1;
	}
	
//	@Override
//	public Position createPosition(Position position)
//	{
//		return bs.createPosition(position);
//	}
}
