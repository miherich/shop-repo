package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteil;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.util.interceptor.Log;
import de.shop.util.Mock;

@Log
@Dependent
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = -5105686816948437276L;
	
	public static final String ARTIKEL_NOT_FOUND = "{artikelverwaltung.notFound.all}";
	public static final String ARTIKEL_NOT_FOUND_ID = "{artikelverwaltung.artikel.notFound.id}";
	

	@NotNull(message = ARTIKEL_NOT_FOUND_ID)
	public AbstractArtikel findArtikelById(int id) {
		// TODO id pruefen
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(id);
	}

	@NotNull(message= ARTIKEL_NOT_FOUND)
	public List<AbstractArtikel> findAllArtikel()
	{
		return Mock.findAllArtikel();
	}
	
	public Fahrrad createFahrrad (Fahrrad fahrrad)
	{
		return Mock.createFahrrad(fahrrad);
	}
	
	public Zubehoer createZubehoer (Zubehoer zubehoer)
	{
		return Mock.createZubehoer(zubehoer);
	}
	
	public Ersatzteil createErsatzteil(Ersatzteil ersatzteil)
	{
		return Mock.createErsatzteil(ersatzteil);
	}
	
	public void updateArtikel (AbstractArtikel artikel)
	{
		 Mock.updateArtikel(artikel);
	}
}
