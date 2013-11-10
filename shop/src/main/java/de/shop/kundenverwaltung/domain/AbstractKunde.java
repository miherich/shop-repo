package de.shop.kundenverwaltung.domain;

import java.util.ArrayList;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import de.shop.bestellverwaltung.domain.Bestellung;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@XmlRootElement
@XmlSeeAlso({ Geschaeftskunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Privatkunde.class, name = AbstractKunde.PRIVATKUNDE),
		@Type(value = Geschaeftskunde.class, name = AbstractKunde.GESCHAEFTSKUNDE) })
public abstract class AbstractKunde implements Serializable {
	private static final long serialVersionUID = 7401524595142572933L;
	
	private int kundennr;
	
	@NotNull(message = "{kundenverwaltung.kunde.nachname.notNull}")
	@Size(min = 2, max = 32, message = "{kundenverwaltung.kunde.nachname.length}")
	@Pattern(regexp = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+", message = "{kunde.nachname.pattern}")
	private String nachname;
	
	@NotNull(message = "{kundenverwaltung.kunde.adresse.notNull}")
	@Valid
	private Adresse adresse;
	
	public static final String PRIVATKUNDE = "P";
	public static final String GESCHAEFTSKUNDE = "G";

	@XmlTransient
	private List<Bestellung> bestellungen;
	private URI bestellURI;

	public int getKundennr() {
		return kundennr;
	}

	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}

	public URI getBestellURI() {
		return bestellURI;
	}

	public void setBestellURI(URI bestellURI) {
		this.bestellURI = bestellURI;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public AbstractKunde() {
		super();
		this.kundennr = 0;
		this.adresse = null;
		this.nachname = null;
		this.bestellungen = new ArrayList<>();
		this.bestellURI = null;
	}

//	public Kunde(int kundennr, Adresse adresse, URI bestellURI, String nachname) {
//		super();
//		this.kundennr = kundennr;
//		this.adresse = adresse;
//		this.nachname = nachname;
//		this.bestellungen = new ArrayList<>();
//		this.bestellURI = bestellURI;
//	}

	@Override
	public String toString() {
		return "Kunde [kundennr=" + kundennr + ", nachname=" + nachname
				+ ", adresse=" + adresse + ", bestellungen=" + bestellungen
				+ ", bestellURI=" + bestellURI + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((bestellURI == null) ? 0 : bestellURI.hashCode());
		result = prime * result
				+ ((bestellungen == null) ? 0 : bestellungen.hashCode());
		result = prime * result + kundennr;
		result = prime * result
				+ ((nachname == null) ? 0 : nachname.hashCode());
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
		final AbstractKunde other = (AbstractKunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		}
		else if (!adresse.equals(other.adresse))
			return false;
		if (bestellURI == null) {
			if (other.bestellURI != null)
				return false;
		}
		else if (!bestellURI.equals(other.bestellURI))
			return false;
		if (bestellungen == null) {
			if (other.bestellungen != null)
				return false;
		}
		else if (!bestellungen.equals(other.bestellungen))
			return false;
		if (kundennr != other.kundennr)
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		}
		else if (!nachname.equals(other.nachname))
			return false;
		return true;
	}
	
	
}
