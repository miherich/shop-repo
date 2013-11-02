package de.shop.artikelverwaltung.domain;

public class Fahrrad extends Artikel {
	private String bezeichnung;
	private String rahmen;
	
	public Fahrrad(int artikelNr, long preis, String bezeichnung, String rahmen) {
		super(artikelNr, preis);
		this.bezeichnung = bezeichnung;
		this.rahmen = rahmen;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getRahmen() {
		return rahmen;
	}

	public void setRahmen(String rahmen) {
		this.rahmen = rahmen;
	}
	

}
