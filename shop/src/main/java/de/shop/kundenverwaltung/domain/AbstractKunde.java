package de.shop.kundenverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import java.net.URI;
import java.util.List;
import static de.shop.util.Constants.KEINE_ID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.ScriptAssert;
//import org.jboss.arquillian.drone.api.annotation.Default;

import de.shop.bestellverwaltung.domain.Bestellung;

//@ScriptAssert(lang = "javascript",
//script = "_this.password != null && !_this.password.equals(\"\")"
//         + " && _this.password.equals(_this.passwordWdh)",
//message = "{kunde.password.notEqual}",
//groups = { Default.class, PasswordGroup.class })

@Entity
//Zu email wird unten ein UNIQUE Index definiert
@Table (name = "kunde", indexes = @Index(columnList = "nachname"))
@Inheritance
@DiscriminatorColumn (name = "art", length = 1)
@NamedQueries({
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN,
             query = "SELECT k"
			        + " FROM   AbstractKunde k"),
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
		        query = "SELECT   k"
				        + " FROM  AbstractKunde k"
		                + " ORDER BY k.id"),
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
	            query = "SELECT k"
				        + " FROM   AbstractKunde k"
	            		+ " WHERE  UPPER(k.nachname) = UPPER(:" + AbstractKunde.PARAM_KUNDE_NACHNAME + ")"),
 	@NamedQuery(name  = AbstractKunde.FIND_KUNDE_BY_EMAIL,
 	            query = "SELECT DISTINCT k"
 			            + " FROM   AbstractKunde k"
 			            + " WHERE  k.email = :" + AbstractKunde.PARAM_KUNDE_EMAIL)
})
@NamedEntityGraphs({
	@NamedEntityGraph(name = AbstractKunde.GRAPH_BESTELLUNGEN,
	attributeNodes = @NamedAttributeNode("bestellungen"))
})

@XmlRootElement
@XmlSeeAlso({ Geschaeftskunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Privatkunde.class, name = AbstractKunde.PRIVATKUNDE),
		@Type(value = Geschaeftskunde.class, name = AbstractKunde.GESCHAEFTSKUNDE) })
public abstract class AbstractKunde implements Serializable {
	private static final long serialVersionUID = 1499437982385399650L;
	
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
	
	private static final String PREFIX = "AbstractKunde.";
	public static final String FIND_KUNDEN = PREFIX + "findKunden";
	public static final String FIND_KUNDEN_ORDER_BY_ID = PREFIX + "findKundenOrderById";
	public static final String FIND_KUNDEN_BY_NACHNAME = PREFIX + "findKundenByNachname";
	public static final String FIND_KUNDE_BY_EMAIL = PREFIX + "findKundeByEmail";
	public static final String PARAM_KUNDE_NACHNAME = "nachname";
	public static final String PARAM_KUNDE_EMAIL = "email";
	public static final String GRAPH_BESTELLUNGEN = PREFIX + "bestellungen";
	
	@Id
	@GeneratedValue
	@Basic (optional = false)
	private Long kundennr = KEINE_ID;
	
	@NotNull(message = NACHNAME_NOTNULL_BV)
	@Size(min = NACHNAME_MIN_PATTERN, max = NACHNAME_MAX_PATTERN,
									message = NACHNAME_LAENGE_BV)
	@Pattern(regexp = NACHNAME_PATTERN, message = NACHNAME_PATTERN_BV)
	private String nachname;
	
	
	@Email(message = EMAIL_PATTERN_BV)
	@NotNull(message = EMAIL_NOTNULL_BV)
	@Size(max = EMAIL_LENGTH_MAX, message = EMAIL_LAENGE_BV)
	@Column(unique = true)
	private String email;
	
	@OneToOne (cascade = { PERSIST, REMOVE }, mappedBy = "kunde")
	@NotNull(message = ADRESSE_NOTNULL_BV)
	@Valid
	private Adresse adresse;

	@OneToMany
	@JoinColumn (name = "kunde_fk", nullable = false)
	@OrderColumn (name = "idx", nullable = false)
	@Transient
	@XmlTransient
	private List<Bestellung> bestellungen;
	
	@Transient
	private URI bestellURI;

	public Long getKundennr() {
		return kundennr;
	}

	public void setKundennr(Long kundennr) {
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
	
	public void addBestellung(Bestellung bestellung) {
		bestellungen.add(bestellung);
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
		 AbstractKunde other = (AbstractKunde) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} 
		else if (!email.equals(other.email))
			return false;
		return true;
	}
}
