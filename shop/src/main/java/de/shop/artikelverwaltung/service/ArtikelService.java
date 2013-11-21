package de.shop.artikelverwaltung.service;

import java.io.Serializable;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.interceptor.Log;
import de.shop.util.Mock;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Log
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;

	public AbstractArtikel findArtikelById(int id) {
		// TODO id pruefen
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(id);
	}
}
