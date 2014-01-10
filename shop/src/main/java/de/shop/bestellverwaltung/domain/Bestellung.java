package de.shop.bestellverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.persistence.AbstractAuditable;


@XmlRootElement
@Entity
@Table(indexes = {
		@Index(columnList = "kunde_fk"),
		@Index(columnList = "erzeugt")
	})
@NamedQueries({
	@NamedQuery(name  = Bestellung.FIND_BESTELLUNGEN,
					query = "SELECT b"
				            + " FROM   Bestellung b")
})
@Cacheable
public class Bestellung extends AbstractAuditable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String BESTELLUNG_BESTELLDATUM_NOTNULL_BV = 
			"{bestellverwaltung.bestellung.bestelldatum.notNull}";
	private static final String BESTELLUNG_ISTAUSGELIEFERT_ASSERTFALSE_BV = 
			"{bestellverwaltung.bestellung.istAusgeliefert.assertFalse}";
	
	private static final String PREFIX = "Bestellung.";
	public static final String FIND_BESTELLUNGEN = PREFIX + "findAllBestellungen";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long bestellnr = KEINE_ID;
	
	@NotNull(message = BESTELLUNG_BESTELLDATUM_NOTNULL_BV)
	private String bestelldatum;		//TODO vernünftiges Datumsformat finden
	@AssertFalse(message = BESTELLUNG_ISTAUSGELIEFERT_ASSERTFALSE_BV) //TODO AssertTrue?
	private boolean istAusgeliefert;
	private boolean mitVerpackung;
	
	@OneToMany(fetch = EAGER, cascade = { PERSIST })
	@JoinColumn(name = "bestellung_fk", nullable = false)
	@NotEmpty(message = "{bestellung.positionen.notEmpty}")
	@Valid
	@Transient //TODO muss hier ein @Transient stehen????
	private List<Position> positionen;
	
	@Transient
	private URI kundeUri;

	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@XmlTransient
	private AbstractKunde kunde;
	
	@XmlElement
	public Date getDatum() {
		return getErzeugt();
	}
	
	public void setDatum(Date datum) {
		setErzeugt(datum);
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellung mit ID=%d", bestellnr);
	}

	public Long getBestellnr() {
		return bestellnr;
	}

	public void setBestellnr(Long bestellnr) {
		this.bestellnr = bestellnr;
	}

	public String getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(String bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public boolean isIstAusgeliefert() {
		return istAusgeliefert;
	}

	public void setIstAusgeliefert(boolean istAusgeliefert) {
		this.istAusgeliefert = istAusgeliefert;
	}

	public boolean isMitVerpackung() {
		return mitVerpackung;
	}

	public void setMitVerpackung(boolean mitVerpackung) {
		this.mitVerpackung = mitVerpackung;
	}

	public List<Position> getPositionen() {
		return positionen;
	}

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}

	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "Bestellung [bestellnr=" + bestellnr + ", bestelldatum="
				+ bestelldatum + ", istAusgeliefert=" + istAusgeliefert
				+ ", mitVerpackung=" + mitVerpackung + ", positionen="
				+ positionen + ", kundeUri=" + kundeUri + ", kunde=" + kunde
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result
				+ ((kundeUri == null) ? 0 : kundeUri.hashCode());
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
		Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		}
		else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (kundeUri == null) {
			if (other.kundeUri != null)
				return false;
		}
		else if (!kundeUri.equals(other.kundeUri))
			return false;
		return true;
	}
}
