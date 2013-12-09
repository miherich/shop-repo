package de.shop.bestellverwaltung.service;

import java.util.List;

import javax.enterprise.context.Dependent;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.interceptor.Log;

@Log
@Dependent
public interface BestellungService {
	Bestellung findBestellungById(int id);
	Position findPositionById(int id, int bid);
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	List<Bestellung> findAllBestellungen();
	Bestellung createBestellung(Bestellung bestellung);
	Position createPosition(Position position);
}
