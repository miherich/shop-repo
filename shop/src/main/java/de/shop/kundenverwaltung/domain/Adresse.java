package de.shop.kundenverwaltung.domain;

import java.net.URI;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
public class Adresse {
	
	private static final String STRASSE_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
	private static final int STRASSE_MIN_LAENGE = 2;
	private static final int STRASSE_MAX_LAENGE = 40;
	private static final int HAUSNUMMER_MIN_LAENGE = 1;
	private static final int HAUSNUMMER_MAX_LAENGE = 4;
	private static final String HAUSNUMMER_PATTERN = "[1-9][0-9]{0,2}[a-z]?";
	private static final String PLZ_PATTERN = "\\d{5}";
	private static final int ORT_MIN_LAENGE = 1;
	private static final int ORT_MAX_LAENGE = 32;
	private static final String ORT_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+"
			+ "(-[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+)?";
	
	private static final String STRASSE_PATTERN_BV = "{kundenverwaltung.adresse.strasse.pattern}";
	private static final String STRASSE_LAENGE_BV = "{kundenverwaltung.adresse.strasse.length}"; 
	private static final String HAUSNUMMER_LAENGE_BV = "{kundenverwaltung.adresse.hausnummer.length}";
	private static final String HAUSNUMMER_PATTERN_BV = "{kundenverwaltung.adresse.hausnummer.pattern}";
	private static final String PLZ_NOTNULL_BV = "{kundenverwaltung.adresse.plz.notNull}";
	private static final String PLZ_PATTERN_BV = "{kundenverwaltung.adresse.plz.pattern}";
	private static final String ORT_LANEGE_BV = "{kundenverwaltung.adresse.ort.length}";
	private static final String ORT_PATTERN_BV = "{kundenverwaltung.adresse.ort.pattern}";
	
	@Id
	@GeneratedValue
	@Basic (optional = false)
	private Long id;

	@Pattern(regexp = STRASSE_PATTERN,
			message = STRASSE_PATTERN_BV)
	@Size(min = STRASSE_MIN_LAENGE, max = STRASSE_MAX_LAENGE, message = STRASSE_LAENGE_BV)
	private String strasse;

	@Size(min = HAUSNUMMER_MIN_LAENGE, max = HAUSNUMMER_MAX_LAENGE, message = HAUSNUMMER_LAENGE_BV)
	@Pattern(regexp = HAUSNUMMER_PATTERN, message = HAUSNUMMER_PATTERN_BV)
	private String hausnummer;
	
	@NotNull (message = PLZ_NOTNULL_BV)
	@Pattern(regexp = PLZ_PATTERN, message = PLZ_PATTERN_BV)
	private String plz;
	
	@Size(min = ORT_MIN_LAENGE, max = ORT_MAX_LAENGE, message = ORT_LANEGE_BV)
	@Pattern(regexp = ORT_PATTERN,
			message = ORT_PATTERN_BV)
	private String ort;
	
	@OneToOne(mappedBy = "adresse")
	@XmlTransient
	private AbstractKunde kunde;
	
	@Transient
	private URI kundeURI;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public AbstractKunde getKunde() {
		return kunde;
	}
	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}
	
	public URI getKundeURI() {
		return kundeURI;
	}
	public void setKundeURI(URI kundeURI) {
		this.kundeURI = kundeURI;
	}
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", strasse=" + strasse + ", hausnummer="
				+ hausnummer + ", plz=" + plz + ", ort=" + ort + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
		result = prime * result
				+ ((kundeURI == null) ? 0 : kundeURI.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
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
		Adresse other = (Adresse) obj;
		if (hausnummer == null) {
			if (other.hausnummer != null)
				return false;
		} else if (!hausnummer.equals(other.hausnummer))
			return false;
		if (kundeURI == null) {
			if (other.kundeURI != null)
				return false;
		} 
		else if (!kundeURI.equals(other.kundeURI))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		}
		else if (!ort.equals(other.ort))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		}
		else if (!plz.equals(other.plz))
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		}
		else if (!strasse.equals(other.strasse))
			return false;
		return true;
	}
}
