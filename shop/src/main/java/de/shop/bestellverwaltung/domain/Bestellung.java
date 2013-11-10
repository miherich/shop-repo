package de.shop.bestellverwaltung.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import de.shop.kundenverwaltung.domain.AbstractKunde;

@XmlRootElement
public class Bestellung {
	@NotEmpty
	private int bestellnr;
	@NotNull
	private String bestelldatum;		//TODO vernünftiges Datumsformat finden
	@AssertFalse //TODO AssertTrue?
	private boolean istAusgeliefert;
	private List<Position> positionen;
	private URI kundeUri;

	@XmlTransient
	private AbstractKunde kunde;

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}

	public int getBestellnr() {
		return bestellnr;
	}

	public void setBestellnr(int bestellnr) {
		this.bestellnr = bestellnr;
	}

	public String getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(String bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public boolean isAusgeliefert() {
		return istAusgeliefert;
	}

	public void setIstAusgeliefert(boolean istAusgeliefert) {
		this.istAusgeliefert = istAusgeliefert;
	}

	public List<Position> getPositionen() {
		return positionen;
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

	public void ausliefern() {
		this.istAusgeliefert = true;
	}

	public Bestellung() {
		super();
		this.bestellnr = 0;
		this.bestelldatum = null;
		this.istAusgeliefert = false;
		this.positionen = new ArrayList<>();
	}

//	public Bestellung(int bestellnr, Date bestelldatum) {
//		super();
//		this.bestellnr = bestellnr;
//		this.bestelldatum = bestelldatum;
//		this.istAusgeliefert = false;
//		this.positionen = new ArrayList<>();
//	}
//
//	public Bestellung(int bestellnr, Date bestelldatum,
//			boolean istAusgeliefert, List<Position> positionen, URI kundeUri,
//			Kunde kunde) {
//		super();
//		this.bestellnr = bestellnr;
//		this.bestelldatum = bestelldatum;
//		this.istAusgeliefert = istAusgeliefert;
//		this.positionen = positionen;
//		this.kundeUri = kundeUri;
//		this.kunde = kunde;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		result = prime * result + bestellnr;
		result = prime * result + (istAusgeliefert ? 1231 : 1237);
		result = prime * result
				+ ((kundeUri == null) ? 0 : kundeUri.hashCode());
		result = prime * result
				+ ((positionen == null) ? 0 : positionen.hashCode());
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
		final Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		} 
		else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (bestellnr != other.bestellnr)
			return false;
		if (istAusgeliefert != other.istAusgeliefert)
			return false;
		if (kundeUri == null) {
			if (other.kundeUri != null)
				return false;
		} 
		else if (!kundeUri.equals(other.kundeUri))
			return false;
		if (positionen == null) {
			if (other.positionen != null)
				return false;
		} 
		else if (!positionen.equals(other.positionen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestellung [bestellnr=" + bestellnr + ", bestelldatum="
				+ bestelldatum + ", istAusgeliefert=" + istAusgeliefert
				+ ", positionen=" + positionen + ", kundeUri=" + kundeUri + "]";
	}
}
