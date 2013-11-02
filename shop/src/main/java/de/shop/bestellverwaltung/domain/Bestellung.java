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
	
	public Bestellung(int bestellnr, Date bestelldatum) {
		super();
		this.bestellnr = bestellnr;
		this.bestelldatum = bestelldatum;
		this.istAusgeliefert = false;
		this.positionen = new ArrayList<>();
	}
}