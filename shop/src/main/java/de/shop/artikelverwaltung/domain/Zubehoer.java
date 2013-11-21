package de.shop.artikelverwaltung.domain;

import javax.enterprise.context.RequestScoped;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@RequestScoped
public class Zubehoer extends AbstractArtikel {

//	public Zubehoer(int artikelNr, long preis) {
//		super(artikelNr, preis);
//	}

	@Override
	public String toString() {
		return "Zubehoer [getArtikelNr()=" + getArtikelNr() + ", getPreis()="
				+ getPreis() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
