package de.shop.artikelverwaltung.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Inheritance
@DiscriminatorValue("Z")
@Entity
public class Zubehoer extends AbstractArtikel {
	private static final long serialVersionUID = -4700709451059606244L;

	@Override
	public String toString() {
		return "Zubehoer [getArtikelNr()=" + getArtikelNr() + ", getPreis()="
				+ getPreis() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
