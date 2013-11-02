package de.shop.bestellverwaltung.domain;

public class Position {
	private long id;
	private String artikel;
	private long anzahl;
	
	public Position(long id, String artikel, long anzahl) {
		super();
		this.id = id;
		this.artikel = artikel;
		this.anzahl = anzahl;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public long getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(long anzahl) {
		this.anzahl = anzahl;
	}
}
