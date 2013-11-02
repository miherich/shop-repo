package de.shop.kundenverwaltung.domain;

public class Geschaeftskunde extends Kunde{
	private String firmenname;
	private String ansprechpartner;
	
	public Geschaeftskunde(int kundennr, Adresse adresse, String firmenname,
			String ansprechpartner) {
		super(kundennr, adresse);
		this.firmenname = firmenname;
		this.ansprechpartner = ansprechpartner;
	}

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	public String getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}
}
