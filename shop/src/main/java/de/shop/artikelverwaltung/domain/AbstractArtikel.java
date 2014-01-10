package de.shop.artikelverwaltung.domain;

import java.math.BigDecimal;

import static de.shop.util.Constants.KEINE_ID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import de.shop.util.persistence.AbstractAuditable;

@XmlRootElement
@Entity
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@Table(name="artikel")
@NamedQueries({
	@NamedQuery(name  = AbstractArtikel.FIND_ARTIKEL,
            	query = "SELECT a"
            	        + " FROM AbstractArtikel a"
				)
})
@XmlSeeAlso({ Zubehoer.class, Fahrrad.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Zubehoer.class, name = AbstractArtikel.ZUBEHOER),
		@Type(value = Fahrrad.class, name = AbstractArtikel.FAHRRAD) })
public abstract class AbstractArtikel extends AbstractAuditable {
	
	private static final long serialVersionUID = -6997989703729888088L;
	private static final String PREFIX = "AbstractArtikel.";
	public static final String FIND_ARTIKEL = PREFIX + "findAllArtikel";

	public static final String PARAM_BEZEICHNUNG = "bezeichnung";
	public static final String PARAM_PREIS = "preis";
	
	private static final String TYP_PATTERN = "[A-Z\00C4\u00D6\u00DC][a-z\u00F6\u00FC\u00DF]+";
	private static final int PREIS_PRECISION = 8;
	private static final int PREIS_SCALE = 2;
	
	private static final String ID_NOTNULL_BV = "{artikelverwaltung.artikel.preis.notNull";
	private static final String TYP_NOTNULL_BV = "{artikelverwaltung.artikel.typ.notNull}";
	private static final String TYP_PATTERN_BV = "{artikelverwaltung.artikel.typ.pattern}";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long artikelNr = KEINE_ID;
	
	@NotNull(message = ID_NOTNULL_BV)
	@Column(precision = PREIS_PRECISION, scale = PREIS_SCALE)
	private BigDecimal preis;
	
	@NotNull(message = TYP_NOTNULL_BV)
	@Pattern(regexp = TYP_PATTERN,
	message = TYP_PATTERN_BV)
	private String typ;		
	//bei Fahrrad: Mountainbike, Trekkingbike, ...; bei Zubehoer: Gepaecktraeger, ...; bei Ersatzteil: Schlauch, ...

	public static final String ZUBEHOER = "Z";
	public static final String FAHRRAD = "F";

	public Long getArtikelNr() {
		return artikelNr;
	}

	public void setArtikelNr(Long artikelNr) {
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

	@Override
	public String toString() {
		return "AbstractArtikel [artikelNr=" + artikelNr + ", preis=" + preis
				+ ", typ=" + typ + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((preis == null) ? 0 : preis.hashCode());
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
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
		final AbstractArtikel other = (AbstractArtikel) obj;
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

}
