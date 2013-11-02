package de.shop.kundenverwaltung.domain;

public class Privatkunde extends Kunde {
	private String vorname;
	private String nachname;
	
	public Privatkunde(int kundennr, Adresse adresse, String vorname,
			String nachname) {
		super(kundennr, adresse);
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	
	
}
