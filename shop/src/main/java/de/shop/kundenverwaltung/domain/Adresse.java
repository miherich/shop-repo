package de.shop.kundenverwaltung.domain;

public class Adresse {
	private String strasse;
	private long hausnummer;
	private long plz;
	private String ort;
	
	public Adresse(String strasse, long hausnummer, long plz, String ort) {
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
	public long getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(long hausnummer) {
		this.hausnummer = hausnummer;
	}
	public long getPlz() {
		return plz;
	}
	public void setPlz(long plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
}
