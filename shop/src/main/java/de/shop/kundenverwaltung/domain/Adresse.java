package de.shop.kundenverwaltung.domain;

public class Adresse {
	private String strasse;
	private String hausnummer;
	private int plz;
	private String ort;
	
	public Adresse() {
		super();
		this.strasse = null;
		this.hausnummer = null;
		this.plz = 0;
		this.ort = null;
	}
	public Adresse(String strasse, String hausnummer, int plz, String ort) {
		super();
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.ort = ort;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
}
