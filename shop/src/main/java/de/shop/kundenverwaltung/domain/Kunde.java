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

	@Override
	public String toString() {
		return "Kunde [kundennr=" + kundennr + ", adresse=" + adresse
				+ ", bestellungen=" + bestellungen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((bestellungen == null) ? 0 : bestellungen.hashCode());
		result = prime * result + kundennr;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kunde other = (Kunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (bestellungen == null) {
			if (other.bestellungen != null)
				return false;
		} else if (!bestellungen.equals(other.bestellungen))
			return false;
		if (kundennr != other.kundennr)
			return false;
		return true;
	}
	
	
}
