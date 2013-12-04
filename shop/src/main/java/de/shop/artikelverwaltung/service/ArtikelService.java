package de.shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.interceptor.Log;
import de.shop.util.Mock;

@Log
@Dependent
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;

	@NotNull(message = "{artikel.notFound.id}")
	public AbstractArtikel findArtikelById(int id) {
		// TODO id pruefen
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(id);
	}
}
