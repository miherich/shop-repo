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
import org.hibernate.validator.constraints.Email;

@XmlRootElement
@XmlSeeAlso({ Geschaeftskunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Privatkunde.class, name = AbstractKunde.PRIVATKUNDE),
		@Type(value = Geschaeftskunde.class, name = AbstractKunde.GESCHAEFTSKUNDE) })
public abstract class AbstractKunde implements Serializable {
	private static final long serialVersionUID = 7401524595142572933L;
	
	public static final String PRIVATKUNDE = "P";
	public static final String GESCHAEFTSKUNDE = "G";
	
	public static final String NACHNAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
	public static final int NACHNAME_MIN_PATTERN = 2;
	public static final int NACHNAME_MAX_PATTERN = 32;
	public static final int EMAIL_LENGTH_MAX = 128;
	
	private static final String NACHNAME_NOTNULL_BV = "{kundenverwaltung.kunde.nachname.notNull}";
	private static final String NACHNAME_LAENGE_BV = "{kundenverwaltung.kunde.nachname.length}";
	private static final String NACHNAME_PATTERN_BV = "{kundenverwaltung.kunde.nachname.pattern}";
	private static final String EMAIL_NOTNULL_BV = "{kundenverwaltung.kunde.email.notNull}";
	private static final String EMAIL_LAENGE_BV = "{kundenverwaltung.kunde.email.length}";
	private static final String EMAIL_PATTERN_BV = "{kundenverwaltung.kunde.email.pattern}";
	private static final String ADRESSE_NOTNULL_BV = "{kundenverwaltung.kunde.adresse.notNull}";
	
	private int kundennr;
	
	@NotNull(message = NACHNAME_NOTNULL_BV)
	@Size(min = NACHNAME_MIN_PATTERN, max = NACHNAME_MAX_PATTERN, message = NACHNAME_LAENGE_BV)
	@Pattern(regexp = NACHNAME_PATTERN, message = NACHNAME_PATTERN_BV)
	private String nachname;
	
	@Email(message = EMAIL_PATTERN_BV)
	@NotNull(message = EMAIL_NOTNULL_BV)
	@Size(max = EMAIL_LENGTH_MAX, message = EMAIL_LAENGE_BV)
	private String email;
	
	@NotNull(message = ADRESSE_NOTNULL_BV)
	@Valid
	private Adresse adresse;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AbstractKunde() {
		super();
		this.kundennr = 0;
		this.adresse = null;
		this.nachname = null;
		this.bestellungen = new ArrayList<>();
		this.bestellURI = null;
	}

	@Override
	public String toString() {
		return "AbstractKunde [kundennr=" + kundennr + ", nachname=" + nachname
				+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} 
		else if (!email.equals(other.email))
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
