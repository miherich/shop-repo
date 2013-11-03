package de.shop.util;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.domain.Ersatzteil;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.Kunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Geschaeftskunde;
import de.shop.kundenverwaltung.domain.Privatkunde;

/**
 * Emulation des Anwendungskerns
 */
public final class Mock {
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;
	private static final int MAX_ARTIKEL = 5;

	public static Kunde findKundeById(int id) {
		if (id > MAX_KUNDEN) {
			return null;
		}

		final Kunde kunde;
		if (id % 2 == 0) {
			kunde = new Privatkunde();
			((Privatkunde) kunde).setVorname("Max");
		} else {
			kunde = new Geschaeftskunde();
			((Geschaeftskunde) kunde).setFirmenname("Musterfirma");
		}

		kunde.setKundennr(id);
		kunde.setNachname("Mustermann");

		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);
		adresse.setStrasse("Musterstrasse");
		adresse.setHausnummer("1");
		adresse.setPlz(12345);
		adresse.setOrt("Musterort");

		kunde.setAdresse(adresse);

		return kunde;
	}

	public static List<Kunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<Kunde> kundeList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(i);
			kundeList.add(kunde);
		}
		return kundeList;
	}

	public static List<Kunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(i);
			kunde.setNachname(nachname);
			kunden.add(kunde);
		}
		return kunden;
	}

	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
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

		final Kunde kunde = findKundeById(id + 1); // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setBestellnr(id);
		bestellung.setBestelldatum("03.11.2013");
		bestellung.setIstAusgeliefert(false);
		bestellung.setKunde(kunde);

		return bestellung;
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

	public static Bestellung createBestellung(Bestellung bestellung) {
		final int nummer = bestellung.getBestelldatum().hashCode();
		bestellung.setBestellnr(nummer);
		System.out.println("Neue Bestellung: " + bestellung);
		return bestellung;
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

		System.out.println("Neuer Kunde: " + kunde);
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

		System.out.println("Neuer Kunde: " + kunde);
		return kunde;
	}

	public static void updateKunde(Kunde kunde) {
		System.out.println("Aktualisierter Kunde: " + kunde);
	}

	public static void deleteKunde(Long kundeId) {
		System.out.println("Kunde mit ID=" + kundeId + " geloescht");
	}

	public static Artikel findArtikelById(int id) {
		if (id > MAX_ARTIKEL) {
			return null;
		}

		final Artikel artikel;
		if (id % 3 == 0) {
			artikel = new Fahrrad();
			artikel.setTyp("Mountainbike");
			((Fahrrad) artikel).setRahmen("Unisex");
			((Fahrrad) artikel).setBezeichnung("Scott Expert Bike");

		} else if (id % 3 == 1) {
			artikel = new Zubehoer();
			artikel.setTyp("Klingel");
		} else {
			artikel = new Ersatzteil();
			artikel.setTyp("Schlauch");
		}

		artikel.setArtikelNr(id);
		artikel.setPreis(499.99);
		return artikel;
	}

	public static List<Artikel> findAllArtikel() {
		final int anzahl = MAX_ARTIKEL;
		final List<Artikel> artikelList = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Artikel artikel = findArtikelById(i);
			artikelList.add(artikel);
		}
		return artikelList;
	}

	public static Fahrrad createFahrrad(Fahrrad fahrrad) {

		// TODO Artikelnummer anpassen (%3=0)
		final String bezeichnung = fahrrad.getBezeichnung();
		fahrrad.setArtikelNr(bezeichnung.length());

		System.out.println("Neuer Artikel Fahrrad: " + fahrrad);
		return fahrrad;
	}

	public static Zubehoer createZubehoer(Zubehoer zubehoer) {
		// TODO Artikelnummer anpassen (%3=1)
		final String typ = zubehoer.getTyp();
		zubehoer.setArtikelNr(typ.length());

		System.out.println("Neuer Artikel Zubehoer: " + zubehoer);
		return zubehoer;
	}

	public static Ersatzteil createErsatzteil(Ersatzteil ersatzteil) {

		// TODO Artikelnummer anpassen (%3=2)
		final String typ = ersatzteil.getTyp();
		ersatzteil.setArtikelNr(typ.length());

		System.out.println("Neuer Artikel Ersatzteil: " + ersatzteil);
		return ersatzteil;
	}

	private Mock() { /**/
	}

	public static void updateArtikel(Artikel artikel) {
		System.out.print("Aktualisierter Artikel: " + artikel);
	}

	public static Artikel createArtikel(Artikel artikel) {
		// TODO Auto-generated method stub
		// Achtung: artikelnr muss richtig gesetzt werden! %3 ==0 -> Fahrrad,
		// %3==1 -> Zubehoer, %3 ==2 ->Ersatzteil

		System.out.print("Neuer Artikel: " + artikel);
		return artikel;
	}
}
