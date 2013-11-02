package de.shop.kundenverwaltung.domain;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Geschaeftskunde extends Kunde{
	private static final long serialVersionUID = 6258156986876418100L;
	private String firmenname;
	private String ansprechpartner;

	public Geschaeftskunde(int kundennr, Adresse adresse, URI bestellURI,
			String firmenname, String ansprechpartner) {
		super(kundennr, adresse, bestellURI);
		this.firmenname = firmenname;
		this.ansprechpartner = ansprechpartner;
	}

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	public String getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	@Override
	public String toString() {
		return "Geschaeftskunde [firmenname=" + firmenname
				+ ", ansprechpartner=" + ansprechpartner + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ansprechpartner == null) ? 0 : ansprechpartner.hashCode());
		result = prime * result
				+ ((firmenname == null) ? 0 : firmenname.hashCode());
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
		Geschaeftskunde other = (Geschaeftskunde) obj;
		if (ansprechpartner == null) {
			if (other.ansprechpartner != null)
				return false;
		} else if (!ansprechpartner.equals(other.ansprechpartner))
			return false;
		if (firmenname == null) {
			if (other.firmenname != null)
				return false;
		} else if (!firmenname.equals(other.firmenname))
			return false;
		return true;
	}
}
