package de.shop.bestellverwaltung.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;


import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.util.interceptor.Log;

@Decorator
@Dependent
@Log
public abstract class BestellungServiceMitGeschenkverpackung implements BestellungService {
	
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
		
		Bestellung bestellung1 = bs.createBestellung(bestellung);
		
		bestellung1.setMitVerpackung(true);
		
		return bestellung1;
	}
	
	@Override
	public Position createPosition(Position position)
	{
		return bs.createPosition(position);
	}
}
