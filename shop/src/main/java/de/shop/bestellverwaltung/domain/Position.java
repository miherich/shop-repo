package de.shop.bestellverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.hibernate.validator.constraints.NotEmpty;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.persistence.AbstractAuditable;

@Entity
@Table(indexes =  {
	@Index(columnList = "bestellung_fk"),
	@Index(columnList = "artikel_fk")
})
@NamedQueries({
 @NamedQuery(name  = Position.FIND_LADENHUETER,
	            query = "SELECT a"
	            	    + " FROM   Artikel a"
	            	    + " WHERE  a NOT IN (SELECT bp.artikel FROM Bestellposition bp)")
})
public class Position extends AbstractAuditable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	public static final String BESTELLUNG_POSITION_NOTNULL_BV = "{bestellverwaltung.position.anzahl.notNull}";
	
	private static final String PREFIX = "Position.";
	public static final String FIND_LADENHUETER = PREFIX + "findLadenhueter";
	private static final int ANZAHL_MIN = 1;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	private Bestellung bestellung;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikel_fk", nullable = false)
	@XmlTransient
	@Valid
	private AbstractArtikel artikel;
	
	@Min(value = ANZAHL_MIN, message = "{bestellposition.anzahl.min}")
	@Basic(optional = false)
	@NotEmpty(message = BESTELLUNG_POSITION_NOTNULL_BV)
	private int anzahl;
	
	@Transient
	private URI bestellURI;
	
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

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public AbstractArtikel getArtikel() {
		return artikel;
	}

	public void setArtikel(AbstractArtikel artikel) {
		this.artikel = artikel;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public URI getBestellURI() {
		return bestellURI;
	}

	public void setBestellURI(URI bestellURI) {
		this.bestellURI = bestellURI;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", bestellung=" + bestellung
				+ ", artikel=" + artikel + ", anzahl=" + anzahl
				+ ", bestellURI=" + bestellURI + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + anzahl;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result
				+ ((bestellURI == null) ? 0 : bestellURI.hashCode());
		result = prime * result
				+ ((bestellung == null) ? 0 : bestellung.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Position other = (Position) obj;
		if (anzahl != other.anzahl)
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (bestellURI == null) {
			if (other.bestellURI != null)
				return false;
		} else if (!bestellURI.equals(other.bestellURI))
			return false;
		if (bestellung == null) {
			if (other.bestellung != null)
				return false;
		} else if (!bestellung.equals(other.bestellung))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
