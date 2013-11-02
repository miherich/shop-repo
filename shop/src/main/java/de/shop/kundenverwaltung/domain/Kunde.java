package de.shop.kundenverwaltung.domain;

import java.util.ArrayList;
import java.util.List;

import de.shop.bestellverwaltung.domain.Bestellung;

public abstract class Kunde {
	private int kundennr;
	private Adresse adresse;
	private List<Bestellung> bestellungen;
	
	public Kunde(int kundennr, Adresse adresse) {
		super();
		this.kundennr = kundennr;
		this.adresse = adresse;
		this.bestellungen = new ArrayList<>();
	}

	public int getKundennr() {
		return kundennr;
	}

	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	
	
}
