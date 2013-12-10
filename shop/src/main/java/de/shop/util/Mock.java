package de.shop.util;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;

//import java.util.Set;
import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteil;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.domain.Position;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Geschaeftskunde;
import de.shop.kundenverwaltung.domain.Privatkunde;

/**
 * Emulation des Anwendungskerns
 */
public final class Mock {
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 4;
	private static final int MAX_BESTELLUNGEN = 10;
	private static final int MAX_POSITIONEN = 2;
	private static final int MAX_ARTIKEL = 4;
	
	private static final int constant1 = 10;
	private static final int constant2 = 3;
	
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	public static AbstractKunde findKundeById(int id) {
		if (id > MAX_KUNDEN) {
			return null;
		}

		final AbstractKunde kunde;
		if (id % 2 == 0) {
			kunde = new Privatkunde();
			((Privatkunde) kunde).setVorname("Max");
		}
		else {
			kunde = new Geschaeftskunde();
			((Geschaeftskunde) kunde).setFirmenname("Musterfirma");
		}

		kunde.setKundennr(id);
		kunde.setNachname("Mustermann");
		kunde.setEmail("mustermail@musterserver.de");

		
		final String plz = "12345";
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);
		adresse.setStrasse("Musterstrasse");
		adresse.setHausnummer("1");
		adresse.setPlz(plz);
		adresse.setOrt("Musterort");

		kunde.setAdresse(adresse);

		return kunde;
	}

	public static List<AbstractKunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<AbstractKunde> kundeList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(i);
			kundeList.add(kunde);
		}
		return kundeList;
	}

	public static List<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = MAX_KUNDEN;
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(i);
			kunde.setNachname(nachname);
			kunden.add(kunde);
		}
		return kunden;
	}
	
	public static AbstractKunde findKundeByEmail(String email) {
		final int id = email.length() % constant1;
		final AbstractKunde kunde = findKundeById(id);
		kunde.setEmail(email);
		
		return kunde;
	}

	public static Privatkunde createPrivatkunde(Privatkunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// TODO IDs passend zu Privatkunde (%2=0) erstellen lassen
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setKundennr(nachname.length());
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((nachname.length()) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);

		LOGGER.infof("Neuer Kunde: %s", kunde);
		return kunde;
	}

	public static Geschaeftskunde createGeschaeftskunde(Geschaeftskunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// TODO IDs passen zu Geschaeftskunde (&2=1) erstellen lassen
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setKundennr(nachname.length());
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((nachname.length()) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);

		LOGGER.infof("Neuer Kunde: %s", kunde);
		return kunde;
	}

	public static void updateKunde(AbstractKunde kunde) {
		final AbstractKunde kundeAlt = findKundeById(kunde.getKundennr());
		kundeAlt.setNachname(kunde.getNachname());
		kundeAlt.setEmail(kunde.getEmail());
		kundeAlt.setAdresse(kunde.getAdresse());
		if (kunde.getKundennr() % 2 == 0) {
			((Privatkunde) kundeAlt).setVorname(((Privatkunde) kunde)
					.getVorname());
		}
		else {
			((Geschaeftskunde) kundeAlt)
					.setFirmenname(((Geschaeftskunde) kunde).getFirmenname());
		}
		LOGGER.infof("Aktualisierter Kunde %s", kunde);
	}

	

	public static List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getKundennr() % MAX_BESTELLUNGEN + 1; // 1, 2,
																		// 3
																		// oder
																		// 4
																		// Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(i);
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);
		}
		kunde.setBestellungen(bestellungen);

		return bestellungen;
	}

	public static Bestellung findBestellungById(int id) {
		if (id > MAX_ID) {
			return null;
		}

		final AbstractKunde kunde = findKundeById(id + 1); // andere ID fuer den Kunden
		

		final Bestellung bestellung = new Bestellung();
		bestellung.setBestellnr(id);
		bestellung.setBestelldatum("03.11.2013");
		bestellung.setIstAusgeliefert(false);
		bestellung.setPositionen(findAllPositionen(id));
		bestellung.setKunde(kunde);

		return bestellung;
	}
	
	public static Position findPositionById(int id, int bid) {
		if (id > MAX_POSITIONEN) {
			return null;
		}

		final AbstractArtikel artikel = findArtikelById(id + 1); // andere ID fuer den Artikel
		final Bestellung bestellung = null;
		final Position position = new Position();
		position.setId(id);
		position.setArtikel(artikel);
		position.setAnzahl((id + constant2) % 2);
		position.setBestellung(bestellung);

		return position;
	}

	public static Position findPositionByIdU(int id) {
		if (id > MAX_POSITIONEN) {
			return null;
		}

		final AbstractArtikel artikel = findArtikelById(id + 1); // andere ID fuer den Artikel
		final Bestellung bestellung = null;
		final Position position = new Position();
		position.setId(id);
		position.setArtikel(artikel);
		position.setAnzahl((id + constant2) % 2);
		position.setBestellung(bestellung);

		return position;
	}


	public static List<Bestellung> findAllBestellungen() {
		final int anzahl = MAX_BESTELLUNGEN;
		final List<Bestellung> bestellungList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(i);
			bestellungList.add(bestellung);
		}
		return bestellungList;
	}
	
	public static List<Position> findAllPositionen(int id) {
		final int anzahl = MAX_POSITIONEN;
		final List<Position> positionList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Position position = findPositionById(i, id);
			positionList.add(position);
		}
		return positionList;
	}
	
//	public static Position findPositionById(int id, int bid) {
//		if (id > MAX_ID) {
//			return null;
//		}
//		
//		Bestellung bestellung = findBestellungById(bid);
//		
//		final int constant = 3;
//		final Position position = new Position();
//		position.setId(id);
//		position.setAnzahl(id + constant);
//		position.setArtikel(findArtikelById(id));
//		position.setBestellung(bestellung);
//
//		return position;
//	}

	public static Bestellung createBestellung(Bestellung bestellung) {
		final int nummer = bestellung.getBestelldatum().hashCode();
		bestellung.setBestellnr(nummer);
		
		LOGGER.infof("Neue %s erzeugt", bestellung);
		
		return bestellung;
	}
	
//	public static Position createPosition(Position position)
//	{
//		final int nummer = position.getArtikel().hashCode();
//		position.setId(nummer);
//		
//		LOGGER.infof("Neue Position %s erzeugt", position);
//
//		return position;
//	}

	public static AbstractArtikel findArtikelById(int id) {
		if (id > MAX_ARTIKEL) {
			return null;
		}

		final AbstractArtikel artikel;
		final int constant = 3;
		if (id % constant == 0) {
			artikel = new Fahrrad();
			artikel.setTyp("Mountainbike");
			((Fahrrad) artikel).setRahmen("U");
			((Fahrrad) artikel).setBezeichnung("Scott Expert Bike");

		}
		else if (id % constant == 1) {
			artikel = new Zubehoer();
			artikel.setTyp("Klingel");
		}
		else {
			artikel = new Ersatzteil();
			artikel.setTyp("Schlauch");
		}

		final BigDecimal preis = new BigDecimal(499.99);
		artikel.setArtikelNr(id);
		artikel.setPreis(preis);
		return artikel;
	}

	public static List<AbstractArtikel> findAllArtikel() {
		final int anzahl = MAX_ARTIKEL;
		final List<AbstractArtikel> artikelList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractArtikel artikel = findArtikelById(i);
			artikelList.add(artikel);
		}
		return artikelList;
	}

	public static Fahrrad createFahrrad(Fahrrad fahrrad) {

		// TODO Artikelnummer anpassen (%3=0)
		final String bezeichnung = fahrrad.getBezeichnung();
		fahrrad.setArtikelNr(bezeichnung.length());

		LOGGER.infof("Neuer Artikel Fahrrad %s", fahrrad);
		return fahrrad;
	}

	public static Zubehoer createZubehoer(Zubehoer zubehoer) {
		// TODO Artikelnummer anpassen (%3=1)
		final String typ = zubehoer.getTyp();
		zubehoer.setArtikelNr(typ.length());

		LOGGER.infof("Neuer Artikel Zubehoer %s", zubehoer);
		return zubehoer;
	}

	public static Ersatzteil createErsatzteil(Ersatzteil ersatzteil) {

		// TODO Artikelnummer anpassen (%3=2)
		final String typ = ersatzteil.getTyp();
		ersatzteil.setArtikelNr(typ.length());

		LOGGER.infof("Neuer Artikel Ersatzteil %s", ersatzteil);
		return ersatzteil;
	}

	private Mock() { /**/
	}

	public static void updateArtikel(AbstractArtikel artikel) {
		final AbstractArtikel artikelAlt = findArtikelById(artikel.getArtikelNr());
		artikelAlt.setPreis(artikel.getPreis());
		artikelAlt.setTyp(artikel.getTyp());
		final int constant = 3;
		if (artikel.getArtikelNr() % constant == 0) {
			((Fahrrad) artikelAlt).setBezeichnung(((Fahrrad) artikel)
					.getBezeichnung());
			((Fahrrad) artikelAlt).setRahmen(((Fahrrad) artikel).getRahmen());
			LOGGER.infof("Aktualisierter Artikel %s", artikel);
		}
		else if (artikel.getArtikelNr() % constant == 1) {
			LOGGER.infof("Aktualisierter Artikel %s", artikel);
		}
		else {
			((Ersatzteil) artikelAlt).setFahrrad(((Ersatzteil) artikel)
					.getFahrrad());
			LOGGER.infof("Aktualisierter Artikel %s", artikel);
		}
	}

}
