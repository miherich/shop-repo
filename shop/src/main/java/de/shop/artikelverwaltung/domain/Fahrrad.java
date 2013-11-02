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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + ((rahmen == null) ? 0 : rahmen.hashCode());
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
		Fahrrad other = (Fahrrad) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (rahmen == null) {
			if (other.rahmen != null)
				return false;
		} else if (!rahmen.equals(other.rahmen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fahrrad [bezeichnung=" + bezeichnung + ", rahmen=" + rahmen
				+ "]";
	}
	

}
