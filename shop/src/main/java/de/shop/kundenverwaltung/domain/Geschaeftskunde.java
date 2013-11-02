package de.shop.kundenverwaltung.domain;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Geschaeftskunde extends Kunde{
	private static final long serialVersionUID = 6258156986876418100L;
	private String firmenname;

	public Geschaeftskunde() {
		super();
		this.firmenname = null;
	}

	public Geschaeftskunde(int kundennr, Adresse adresse, URI bestellURI,
			String nachname, String firmenname) {
		super(kundennr, adresse, bestellURI, nachname);
		this.firmenname = firmenname;
	}

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	@Override
	public String toString() {
		return "Geschaeftskunde [firmenname=" + firmenname + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (firmenname == null) {
			if (other.firmenname != null)
				return false;
		} else if (!firmenname.equals(other.firmenname))
			return false;
		return true;
	}
}
