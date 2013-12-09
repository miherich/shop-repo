package de.shop.bestellverwaltung.domain;

import java.net.URI;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import de.shop.artikelverwaltung.domain.AbstractArtikel;

@XmlRootElement
public class Position {
	private int id;
	private Bestellung bestellung;
	@Valid
	private AbstractArtikel artikel;
	@NotEmpty(message = "{bestellverwaltung.position.anzahl.notNull}")
	private int anzahl;
	private URI bestellURI;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzahl;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result
				+ ((bestellURI == null) ? 0 : bestellURI.hashCode());
		result = prime * result
				+ ((bestellung == null) ? 0 : bestellung.hashCode());
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", bestellung=" + bestellung
				+ ", artikel=" + artikel + ", anzahl=" + anzahl
				+ ", bestellURI=" + bestellURI + "]";
	}
}
