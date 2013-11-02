package de.shop.bestellverwaltung.domain;

import java.util.Date;

public class Bestellung {
	private int bestellnr;
	private Date bestelldatum;
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
	public List<Position> getPositionen() {
		return positionen;
	}
	
	public Bestellung(int bestellnr, Date bestelldatum) {
		super();
		this.bestellnr = bestellnr;
		this.bestelldatum = bestelldatum;
	}
}