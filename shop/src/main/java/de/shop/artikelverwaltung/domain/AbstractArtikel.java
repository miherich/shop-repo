package de.shop.artikelverwaltung.domain;

import java.math.BigDecimal;
import java.net.URI;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	private static final String ID_NOTNULL_BV = "{artikelverwaltung.artikel.preis.notNull";
	private static final String TYP_NOTNULL_BV = "{artikelverwaltung.artikel.typ.notNull}";
	private static final String TYP_PATTERN = "{artikelverwaltung.artikel.typ.pattern}";
	
	private int artikelNr;
	
	@NotNull(message = ID_NOTNULL_BV)
	@Column(precision = 8, scale = 2)
	private BigDecimal preis;
	
	@NotNull(message = TYP_NOTNULL_BV)
	@Pattern(regexp = "[A-Z\00C4\u00D6\u00DC][a-z\u00F6\u00FC\u00DF]+",
	message = TYP_PATTERN )
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

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
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

	@Override
	public String toString() {
		return "AbstractArtikel [artikelNr=" + artikelNr + ", preis=" + preis
				+ ", typ=" + typ + ", artikelUri=" + artikelUri + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artikelNr;
		result = prime * result
				+ ((artikelUri == null) ? 0 : artikelUri.hashCode());
		result = prime * result + ((preis == null) ? 0 : preis.hashCode());
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
		if (preis == null) {
			if (other.preis != null)
				return false;
		}
		else if (!preis.equals(other.preis))
			return false;
		if (typ == null) {
			if (other.typ != null)
				return false;
		} 
		else if (!typ.equals(other.typ))
			return false;
		return true;
	}




//	public Artikel build() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
