package de.shop.kundenverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.logging.Logger;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Geschaeftskunde;
import de.shop.kundenverwaltung.domain.Privatkunde;
import de.shop.kundenverwaltung.rest.KundeResource;
import de.shop.util.interceptor.Log;
import de.shop.util.rest.NotFoundException;
import de.shop.util.Mock;

@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}

	public AbstractKunde findKundeById(int id) {
//		if (id == null) {
//			return null;
//		}
		// TODO Datenbanzugriffsschicht statt Mock
		final AbstractKunde kunde = Mock.findKundeById(id);
		return kunde;
	}
	
	public List<AbstractKunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt Mock
		final List<AbstractKunde> kunden = Mock.findAllKunden();
		return kunden;
	}
	
	/**
	 */
	public List<AbstractKunde> findKundenByNachname(String nachname) {
		// TODO Datenbanzugriffsschicht statt Mock
		List<AbstractKunde> kunden = Mock.findKundenByNachname(nachname);
		return kunden;
	}

	public Geschaeftskunde createGeschaeftskunde(Geschaeftskunde kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
//		if (Mock.findKundeByEmail(kunde.getEmail()) != null) {
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
//		if (Mock.findKundeByEmail(kunde.getEmail()) != null) {
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
		final AbstractKunde vorhandenerKunde = Mock.findKundeById(kunde.getKundennr());

		// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
		if (vorhandenerKunde == null) {
			throw new NotFoundException(KundeResource.KUNDE_NOT_FOUND_ID, kunde.getKundennr());
		}
		
		// TODO Datenbanzugriffsschicht statt Mock
		Mock.updateKunde(kunde);
		
		return kunde;
	}
}
