package de.shop.artikelverwaltung.domain;

public class Zubehoer extends Artikel {

	public Zubehoer(int artikelNr, long preis) {
		super(artikelNr, preis);
	}

	@Override
	public String toString() {
		return "Zubehoer [getArtikelNr()=" + getArtikelNr() + ", getPreis()="
				+ getPreis() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
