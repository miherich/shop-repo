package de.shop.bestellverwaltung.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bestellung {
	private int bestellnr;
	private Date bestelldatum;
	private boolean istAusgeliefert;
	private List<Position> positionen;

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}
	public int getBestellnr() {
		return bestellnr;
	}
	public void setBestellnr(int bestellnr) {
		this.bestellnr = bestellnr;
	}
	public Date getBestelldatum() {
		return bestelldatum;
	}
	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}
	public boolean getIstAusgeliefert() {
		return istAusgeliefert;
	}
	public void setIstAusgeliefert(boolean istAusgeliefert) {
		this.istAusgeliefert = istAusgeliefert;
	}
	public List<Position> getPositionen() {
		return positionen;
	}
	
	public void Ausliefern() {
		this.istAusgeliefert = true;
	}
	
	public Bestellung(int bestellnr, Date bestelldatum) {
		super();
		this.bestellnr = bestellnr;
		this.bestelldatum = bestelldatum;
		this.istAusgeliefert = false;
		this.positionen = new ArrayList<>();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + bestellnr;
		result = prime * result + (istAusgeliefert ? 1231 : 1237);
		result = prime * result
				+ ((positionen == null) ? 0 : positionen.hashCode());
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
		Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		} else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (bestellnr != other.bestellnr)
			return false;
		if (istAusgeliefert != other.istAusgeliefert)
			return false;
		if (positionen == null) {
			if (other.positionen != null)
				return false;
		} else if (!positionen.equals(other.positionen))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bestellung [bestellnr=" + bestellnr + ", bestelldatum="
				+ bestelldatum + ", istAusgeliefert=" + istAusgeliefert
				+ ", positionen=" + positionen + "]";
	}
}