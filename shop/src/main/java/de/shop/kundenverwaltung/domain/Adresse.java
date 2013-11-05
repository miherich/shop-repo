package de.shop.kundenverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse {
	private int id;
	private String strasse;
	private String hausnummer;
	private int plz;
	private String ort;
	private AbstractKunde kunde;

	public Adresse() {
		super();
		this.strasse = null;
		this.hausnummer = null;
		this.plz = 0;
		this.ort = null;
		this.kunde = null;
	}

//	public Adresse(int id, String strasse, String hausnummer, int plz,
//			String ort, Kunde kunde) {
//		super();
//		this.id = id;
//		this.strasse = strasse;
//		this.hausnummer = hausnummer;
//		this.plz = plz;
//		this.ort = ort;
//		this.kunde = kunde;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", hausnummer=" + hausnummer
				+ ", plz=" + plz + ", ort=" + ort + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + plz;
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Adresse other = (Adresse) obj;
		if (hausnummer == null) {
			if (other.hausnummer != null)
				return false;
		}
		else if (!hausnummer.equals(other.hausnummer))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} 
		else if (!ort.equals(other.ort))
			return false;
		if (plz != other.plz)
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		}
		else if (!strasse.equals(other.strasse))
			return false;
		return true;
	}

}
