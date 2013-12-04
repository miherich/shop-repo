package de.shop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Geschaeftskunde;
import de.shop.kundenverwaltung.domain.Privatkunde;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;

	@NotNull(message = "{kunde.notFound.id}")
	public AbstractKunde findKundeById(int id) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findKundeById(id);
	}
	
	@NotNull(message = "{kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
		if (email == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findKundeByEmail(email);
	}
	
	public List<AbstractKunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findAllKunden();
	}
	
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<AbstractKunde> findKundenByNachname(String nachname) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findKundenByNachname(nachname);
	}

	public Geschaeftskunde createGeschaeftskunde(Geschaeftskunde kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
//		if (findKundeByEmail(kunde.getEmail()) != null) {
//			throw new EmailExistsException(kunde.getEmail());
//		}

		kunde = Mock.createGeschaeftskunde(kunde);

		return kunde;
	}
	
	public Privatkunde createPrivatkunde(Privatkunde kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
//		if (findKundeByEmail(kunde.getEmail()) != null) {
//			throw new EmailExistsException(kunde.getEmail());
//		}

		kunde = Mock.createPrivatkunde(kunde);

		return kunde;
	}

	
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		final AbstractKunde vorhandenerKunde = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (vorhandenerKunde != null) {
			// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
			if (vorhandenerKunde.getKundennr() != kunde.getKundennr()) {
				throw new EmailExistsException(kunde.getEmail());
			}
		}
		

		// TODO Datenbanzugriffsschicht statt Mock
		Mock.updateKunde(kunde);
		
		return kunde;
	}
}
