package de.shop.kundenverwaltung.domain;

//import java.net.URI;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Inheritance
@DiscriminatorValue("G")
public class Geschaeftskunde extends AbstractKunde {
	private static final long serialVersionUID = 3578576107698691752L;
	
		private static final String GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_BV = 
								"{kundenverwaltung.geschaeftskunde.firmenname.length}";
	private static final int GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_MIN = 2;
	private static final int GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_MAX = 64;

	
	
	@Size(min = GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_MIN, 
		max = GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_MAX,
		message = GESCHAEFTSKUNDE_FIRMENNAME_LENGTH_BV)
	private String firmenname;

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	@Override
	public String toString() {
		return "Geschaeftskunde [firmenname=" + firmenname + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((firmenname == null) ? 0 : firmenname.hashCode());
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
		final Geschaeftskunde other = (Geschaeftskunde) obj;
		if (firmenname == null) {
			if (other.firmenname != null)
				return false;
		}
		else if (!firmenname.equals(other.firmenname))
			return false;
		return true;
	}
}
