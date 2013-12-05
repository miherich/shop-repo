package de.shop.bestellverwaltung.service;

import java.util.List;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.interceptor.Log;

@Log
public interface BestellungService {
	Bestellung findBestellungById(int id);
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	List<Bestellung> findAllBestellungen();
	Bestellung createBestellung(Bestellung bestellung);
}
