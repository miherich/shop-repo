package de.shop.kundenverwaltung.domain;

import java.util.ArrayList;
import java.util.List;

import de.shop.bestellverwaltung.domain.Bestellung;

public abstract class Kunde {
	private int id;
	private Adresse adresse;
	private List<Bestellung> bestellungen;
	
	public Kunde(int id) {
		super();
		this.id = id;
		this.adresse = new Adresse();
		this.bestellungen = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
