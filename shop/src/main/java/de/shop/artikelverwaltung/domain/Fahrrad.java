package de.shop.artikelverwaltung.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@Table(indexes = @Index(columnList = "bezeichnung"))
public class Fahrrad extends AbstractArtikel {
	private static final long serialVersionUID = 9077088903116091916L;
	
	private static final int BEZEICHNUNG_MIN_LAENGE = 2;
	private static final int BEZEICHNUNG_MAX_LAENGE = 150;
	private static final int RAHMEN_MIN_LAENGE = 1;
	private static final int RAHMEN_MAX_LAENGE = 1;
	private static final String RAHMEN_PATTERN = "[M,W,U]";
	
	private static final String BEZEICHNUNG_SIZE_BV = "{artikelverwaltung.fahrrad.bezeichnung.size}";
	
	private static final String RAHMEN_SIZE_BV = "{artikelverwaltung.fahrrad.rahmen.size}";
	private static final String RAHMEN_PATTERN_BV = "{artikelverwaltung.fahrrad.rahmen.pattern}";
			
	@Size(min = BEZEICHNUNG_MIN_LAENGE, max = BEZEICHNUNG_MAX_LAENGE, message = BEZEICHNUNG_SIZE_BV)
	private String bezeichnung;
	
	
	@Size(min = RAHMEN_MIN_LAENGE , max = RAHMEN_MAX_LAENGE , message = RAHMEN_SIZE_BV)
	@Pattern(regexp = RAHMEN_PATTERN, message = RAHMEN_PATTERN_BV)
	private String rahmen;
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
	public String getRahmen() {
		return rahmen;
	}

	public void setRahmen(String rahmen) {
		this.rahmen = rahmen;
	}

	@Override
	public String toString() {
		return "Fahrrad [bezeichnung=" + bezeichnung + ", rahmen=" + rahmen
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + ((rahmen == null) ? 0 : rahmen.hashCode());
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
		final Fahrrad other = (Fahrrad) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		}
		else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (rahmen == null) {
			if (other.rahmen != null)
				return false;
		} 
		else if (!rahmen.equals(other.rahmen))
			return false;
		return true;
	}

}
