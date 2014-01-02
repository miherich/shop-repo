package de.shop.kundenverwaltung.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Produces;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Geschaeftskunde;
import de.shop.kundenverwaltung.domain.Privatkunde;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;

@Dependent
@Log
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
	TEXT_XML + ";qs=0.5" })
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;

	public static final String KUNDE_NOT_FOUND = "{kundenverwaltung.kunde.notFound.all}";
	public static final String KUNDE_NOT_FOUND_ID = "{kundenverwaltung.kunde.notFound.id}";
	public static final String KUNDE_NOT_FOUND_NACHNAME = "{kundenverwaltung.kunde.notFound.nachname}";
	public static final String KUNDE_NOT_FOUND_MAIL = "{kundenverwaltung.kunde.notFound.email}";
	
	@NotNull(message = KUNDE_NOT_FOUND_ID)
		public AbstractKunde findKundeById(Long id) {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findKundeById(id);
	}
	
	@NotNull(message = KUNDE_NOT_FOUND_MAIL)
	public AbstractKunde findKundeByEmail(String email) {
		if (email == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findKundeByEmail(email);
	}
	
	@NotNull(message = KUNDE_NOT_FOUND)
	public List<AbstractKunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findAllKunden();
	}
	
	@Size(min = 1, message = KUNDE_NOT_FOUND_NACHNAME)
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

//		TODO Kommentare rausnehmen, wenn DB-Zugriffsschicht implementiert
		// Pruefung, ob die Email-Adresse schon existiert
//		final AbstractKunde vorhandenerKunde = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
//		if (vorhandenerKunde != null) {
			// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
//			if (vorhandenerKunde.getKundennr() != kunde.getKundennr()) {
//				throw new EmailExistsException(kunde.getEmail());
//			}
//		}
		

		// TODO Datenbanzugriffsschicht statt Mock
		Mock.updateKunde(kunde);
		
		return kunde;
	}
}
