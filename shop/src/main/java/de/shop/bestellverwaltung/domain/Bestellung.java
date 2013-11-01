package de.shop.bestellverwaltung.domain;

import java.util.Date;

public class Bestellung {
	private int bestellnr;
	private Date bestelldatum;
	//TODO Position anlegen
	
	public Bestellung(int bestellnr, Date bestelldatum) {
		super();
		this.bestellnr = bestellnr;
		this.bestelldatum = bestelldatum;
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
}
