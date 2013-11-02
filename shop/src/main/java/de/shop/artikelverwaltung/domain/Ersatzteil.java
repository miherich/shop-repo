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

	@Override
	public String toString() {
		return "Ersatzteil [fahrrad=" + fahrrad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fahrrad == null) ? 0 : fahrrad.hashCode());
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
		Ersatzteil other = (Ersatzteil) obj;
		if (fahrrad == null) {
			if (other.fahrrad != null)
				return false;
		} else if (!fahrrad.equals(other.fahrrad))
			return false;
		return true;
	}
}
