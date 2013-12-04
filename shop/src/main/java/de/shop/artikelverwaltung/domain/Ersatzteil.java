package de.shop.artikelverwaltung.domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@RequestScoped
public class Ersatzteil extends AbstractArtikel {
	@Inject
	@Valid
	private Fahrrad fahrrad;

	public Fahrrad getFahrrad() {
		return fahrrad;
	}

	public void setFahrrad(Fahrrad fahrrad) {
		this.fahrrad = fahrrad;
	}

	public Ersatzteil() {
		super();
		this.fahrrad = null;
	}

	// public Ersatzteil(Fahrrad fahrrad) {
	// super();
	// this.fahrrad = fahrrad;
	// }

	@Override
	public String toString() {
		return "Ersatzteil [fahrrad=" + fahrrad + ", toString()="
				+ super.toString() + "]";
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
		final Ersatzteil other = (Ersatzteil) obj;
		if (fahrrad == null) {
			if (other.fahrrad != null)
				return false;
		} 
		else if (!fahrrad.equals(other.fahrrad))
			return false;
		return true;
	}

}
