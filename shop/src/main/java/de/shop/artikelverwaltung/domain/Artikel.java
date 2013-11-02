package de.shop.artikelverwaltung.domain;

public class Artikel {
	private long artikelNr;
	private long preis;
	
	public long getArtikelNr() {
		return artikelNr;
	}
	public void setArtikelNr(long artikelNr) {
		this.artikelNr = artikelNr;
	}
	public long getPreis() {
		return preis;
	}
	public void setPreis(long preis) {
		this.preis = preis;
	}
	public Artikel(long artikelNr, long preis) {
		super();
		this.artikelNr = artikelNr;
		this.preis = preis;
	};
	

}
