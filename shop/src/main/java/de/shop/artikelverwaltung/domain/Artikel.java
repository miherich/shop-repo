package de.shop.artikelverwaltung.domain;

public abstract class Artikel {
	private int artikelNr;
	private long preis;
	
	public long getArtikelNr() {
		return artikelNr;
	}
	public void setArtikelNr(int artikelNr) {
		this.artikelNr = artikelNr;
	}
	public long getPreis() {
		return preis;
	}
	public void setPreis(long preis) {
		this.preis = preis;
	}
	public Artikel(int artikelNr, long preis) {
		super();
		this.artikelNr = artikelNr;
		this.preis = preis;
	};
	

}
