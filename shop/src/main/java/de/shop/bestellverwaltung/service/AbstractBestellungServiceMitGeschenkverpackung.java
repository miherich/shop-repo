package de.shop.bestellverwaltung.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.util.interceptor.Log;

@Decorator
@Dependent
@Log
public abstract class AbstractBestellungServiceMitGeschenkverpackung implements BestellungService {
	
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	@Inject
	@Delegate
	@Any
	private BestellungService bs;

	@Override
	public Bestellung findBestellungById(Long id) {
		return bs.findBestellungById(id);
	}

	@Override
	public Position findPositionById(Long id, Long bid) {
		return bs.findPositionById(id, bid);
	}
	
	@Override
	public List<Bestellung> findAllBestellungen() {
		return bs.findAllBestellungen();
	}
	
	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundenNr) {
		
		final Bestellung bestellung1 = bs.createBestellung(bestellung, kundenNr);
		
		bestellung1.setMitVerpackung(true);
		
		LOGGER.infof("Jetzt ist ne Geschenkverpackung um die Bestellung %s rum", bestellung);
		
		return bestellung1;
	}
}
