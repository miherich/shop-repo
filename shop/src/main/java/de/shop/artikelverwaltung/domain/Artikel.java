package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public abstract class Artikel {
	private int artikelNr;
	private long preis;
	
	public long getArtikelNr() {
		return artikelNr;
	}
	public void setArtikelNr(int artikelNr) {
		this.artikelNr = artikelNr;
	}
	public long getPreis() {
		return preis;
	}
	public void setPreis(long preis) {
		this.preis = preis;
	}
	public Artikel(int artikelNr, long preis) {
		super();
		this.artikelNr = artikelNr;
		this.preis = preis;
	}
	@Override
	public String toString() {
		return "Artikel [artikelNr=" + artikelNr + ", preis=" + preis + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artikelNr;
		result = prime * result + (int) (preis ^ (preis >>> 32));
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
		Artikel other = (Artikel) obj;
		if (artikelNr != other.artikelNr)
			return false;
		if (preis != other.preis)
			return false;
		return true;
	};
}
