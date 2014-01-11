package de.shop.bestellverwaltung.service;

import java.util.List;

import javax.enterprise.context.Dependent;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.interceptor.Log;

@Log
@Dependent
public interface BestellungService {
	Bestellung findBestellungById(Long id);
	List<Bestellung> findAllBestellungen();
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	Bestellung createBestellung(Bestellung bestellung, Long kundenNr);
	Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde);
}
