package de.shop.bestellverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;
import org.jboss.logging.Logger;
import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.persistence.AbstractAuditable;

@Entity
public class Position extends AbstractAuditable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	public static final String BESTELLUNG_POSITION_NOTNULL_BV = "{bestellverwaltung.position.anzahl.notNull}";
	
	private static final String PREFIX = "Position.";
	public static final String FIND_POSITION_BY_ID = PREFIX + "findPositionById";
	
	public static final String PARAM_POSITION_ID = "id";
	public static final String PARAM_BESTELLUNG_ID = "bid";
	private static final int ANZAHL_MIN = 1;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikel_fk", nullable = false)
	@XmlTransient
	private AbstractArtikel artikel;
	
	@Min(value = ANZAHL_MIN, message = "{bestellposition.anzahl.min}")
	@Basic(optional = false)
	private short anzahl;
	
	@Transient
	private URI bestellURI;
	
	@Transient
	private URI artikelURI;
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellposition mit ID=%d", id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractArtikel getArtikel() {
		return artikel;
	}

	public void setArtikel(AbstractArtikel artikel) {
		this.artikel = artikel;
	}

	public short getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(short anzahl) {
		this.anzahl = anzahl;
	}

	public URI getBestellURI() {
		return bestellURI;
	}

	public void setBestellURI(URI bestellURI) {
		this.bestellURI = bestellURI;
	}

	public URI getArtikelURI() {
		return artikelURI;
	}

	public void setArtikelURI(URI artikelURI) {
		this.artikelURI = artikelURI;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", anzahl=" + anzahl + ", bestellURI="
				+ bestellURI + ", artikelURI=" + artikelURI + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + anzahl;
		result = prime * result
				+ ((artikelURI == null) ? 0 : artikelURI.hashCode());
		result = prime * result
				+ ((bestellURI == null) ? 0 : bestellURI.hashCode());
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
		final Position other = (Position) obj;
		if (anzahl != other.anzahl)
			return false;
		if (artikelURI == null) {
			if (other.artikelURI != null)
				return false;
		} 
		else if (!artikelURI.equals(other.artikelURI))
			return false;
		if (bestellURI == null) {
			if (other.bestellURI != null)
				return false;
		} 
		else if (!bestellURI.equals(other.bestellURI))
			return false;
		return true;
	}
}
