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
@JsonSubTypes({ @Type(value = Zubehoer.class, name = AbstractArtikel.ZUBEHOER),
		@Type(value = Fahrrad.class, name = AbstractArtikel.FAHRRAD),
		@Type(value = Ersatzteil.class, name = AbstractArtikel.ERSATZTEIL) })
public abstract class AbstractArtikel {
	private int artikelNr;
	private double preis;
	private String typ;		
	//bei Fahrrad: Mountainbike, Trekkingbike, ...; bei Zubehoer: Gepaecktraeger, ...; bei Ersatzteil: Schlauch, ...
	private URI artikelUri;

	public static final String ZUBEHOER = "Z";
	public static final String FAHRRAD = "F";
	public static final String ERSATZTEIL = "E";

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

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public URI getArtikelUri() {
		return artikelUri;
	}

	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
	}

	public AbstractArtikel() {
		super();
		this.artikelNr = 0;
		this.preis = 0.0;
		this.artikelUri = null;
	}

	@Override
	public String toString() {
		return "Artikel [artikelNr=" + artikelNr + ", preis=" + preis
				+ ", typ=" + typ + ", artikelUri=" + artikelUri + "]";
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
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
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
		final AbstractArtikel other = (AbstractArtikel) obj;
		if (artikelNr != other.artikelNr)
			return false;
		if (artikelUri == null) {
			if (other.artikelUri != null)
				return false;
		} 
		else if (!artikelUri.equals(other.artikelUri))
			return false;
		if (Double.doubleToLongBits(preis) != Double
				.doubleToLongBits(other.preis))
			return false;
		if (typ == null) {
			if (other.typ != null)
				return false;
		}
		else if (!typ.equals(other.typ))
			return false;
		return true;
	}

//	public Artikel(int artikelNr, long preis) {
//		super();
//		this.artikelNr = artikelNr;
//		this.preis = preis;
//		this.artikelUri = null;
//	}



//	public Artikel build() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
