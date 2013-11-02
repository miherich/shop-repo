package de.shop.kundenverwaltung.domain;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Privatkunde extends Kunde {
	private static final long serialVersionUID = -3177911520687689458L;
	private String vorname;

	public Privatkunde() {
		super();
		this.vorname = null;
	}
	
//	public Privatkunde(int kundennr, Adresse adresse, URI bestellURI,
//			String nachname, String vorname) {
//		super(kundennr, adresse, bestellURI, nachname);
//		this.vorname = vorname;
//	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@Override
	public String toString() {
		return "Privatkunde [vorname=" + vorname + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		Privatkunde other = (Privatkunde) obj;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}
	
	
}
