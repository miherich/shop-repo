package de.shop.artikelverwaltung.domain;

public class Ersatzteil {
	private Fahrrad fahrrad;

	public Fahrrad getFahrrad() {
		return fahrrad;
	}

	public void setFahrrad(Fahrrad fahrrad) {
		this.fahrrad = fahrrad;
	}

	public Ersatzteil(Fahrrad fahrrad) {
		super();
		this.fahrrad = fahrrad;
	}
	

}
