package de.shop.artikelverwaltung.domain;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@XmlRootElement
@XmlSeeAlso({ Zubehoer.class, Fahrrad.class, Ersatzteil.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Zubehoer.class, name = Artikel.Zubehoer),
		@Type(value = Fahrrad.class, name = Artikel.Fahrrad),
		@Type(value = Ersatzteil.class, name = Artikel.Ersatzteil) })
public abstract class Artikel {
	private int artikelNr;
	private double preis;
	private URI artikelUri;

	public static final String Zubehoer = "Z";
	public static final String Fahrrad = "F";
	public static final String Ersatzteil = "E";

	public int getArtikelNr() {
		return artikelNr;
	}

	public void setArtikelNr(int artikelNr) {
		this.artikelNr = artikelNr;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public URI getArtikelUri() {
		return artikelUri;
	}

	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
	}

	public Artikel() {
		super();
		this.artikelNr = 0;
		this.preis = 0.0;
		this.artikelUri = null;
	}

//	public Artikel(int artikelNr, long preis) {
//		super();
//		this.artikelNr = artikelNr;
//		this.preis = preis;
//		this.artikelUri = null;
//	}

	@Override
	public String toString() {
		return "Artikel [artikelNr=" + artikelNr + ", preis=" + preis + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artikelNr;
		result = prime * result
				+ ((artikelUri == null) ? 0 : artikelUri.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (artikelUri == null) {
			if (other.artikelUri != null)
				return false;
		} else if (!artikelUri.equals(other.artikelUri))
			return false;
		if (Double.doubleToLongBits(preis) != Double
				.doubleToLongBits(other.preis))
			return false;
		return true;
	}

//	public Artikel build() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
