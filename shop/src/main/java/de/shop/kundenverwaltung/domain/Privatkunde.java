package de.shop.kundenverwaltung.domain;

//import java.net.URI;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Privatkunde extends AbstractKunde {
	private static final long serialVersionUID = -3177911520687689458L;
	private static final String PRIVATKUNDE_VORNAME_NOTNULL_BV = "{kundenverwaltung.privatkunde.vorname.notNull}";
	private static final String PRIVATKUNDE_VORNAME_LENGTH_BV = "{kundenverwaltung.privatkunde.vorname.size}";
	private static final String PRIVATKUNDE_VORNAME_PATTERN_BV = "{kundenverwaltung.privatkunde.vorname.pattern}";
	
	@NotNull(message = PRIVATKUNDE_VORNAME_NOTNULL_BV)
	@Size(min = 2, max = 32, message = PRIVATKUNDE_VORNAME_LENGTH_BV)
	@Pattern(regexp = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+",
	message = PRIVATKUNDE_VORNAME_PATTERN_BV)
	private String vorname;

	public Privatkunde() {
		super();
		this.vorname = null;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@Override
	public String toString() {
		return "Privatkunde [vorname=" + vorname + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		final Privatkunde other = (Privatkunde) obj;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		}
		else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}
	
	
}
