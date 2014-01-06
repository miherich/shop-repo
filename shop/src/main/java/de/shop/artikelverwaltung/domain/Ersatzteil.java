package de.shop.artikelverwaltung.domain;

import java.net.URI;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
//@Table(indexes = @Index(columnList = "bezeichnung"))
@XmlRootElement
public class Ersatzteil extends AbstractArtikel {
	private static final long serialVersionUID = 9217897238630488917L;

	@Valid
	private Fahrrad fahrrad;
	
	@Transient
	private URI fahrradURI;

	public Fahrrad getFahrrad() {
		return fahrrad;
	}

	public void setFahrrad(Fahrrad fahrrad) {
		this.fahrrad = fahrrad;
	}

	public URI getFahrradURI() {
		return fahrradURI;
	}

	public void setFahrradURI(URI fahrradURI) {
		this.fahrradURI = fahrradURI;
	}

	@Override
	public String toString() {
		return "Ersatzteil [fahrrad=" + fahrrad + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((fahrradURI == null) ? 0 : fahrradURI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ersatzteil other = (Ersatzteil) obj;
		if (fahrradURI == null) {
			if (other.fahrradURI != null)
				return false;
		} else if (!fahrradURI.equals(other.fahrradURI))
			return false;
		return true;
	}
}
