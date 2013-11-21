package de.shop.bestellverwaltung.rest;

import static de.shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.shop.kundenverwaltung.rest.KundeResource;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.rest.UriHelper;
import de.shop.util.rest.NotFoundException;
import de.shop.util.Mock;

@Path("/bestellungen")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
public class BestellungResource {
	
	public static final String BESTELLUNG_ID_PATH_PARAM = "id";
	
	@Context
	private UriInfo uriInfo;

	@Inject
	private UriHelper uriHelper;

	@Inject
	private KundeResource kundeResource;

	@GET
	public Response findAllBestellungen() {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final List<Bestellung> bestellungList = Mock.findAllBestellungen();
		if (bestellungList.isEmpty())
			throw new NotFoundException(
					"Es wurden keine Bestellungen gefunden.");
		return Response.ok(new GenericEntity<List<Bestellung>>(bestellungList) {
		}).build();
	}

	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam(BESTELLUNG_ID_PATH_PARAM) int id) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Bestellung bestellung = Mock.findBestellungById(id);
		if (bestellung == null) {
			throw new NotFoundException("Keine Bestellung mit der ID " + id
					+ " gefunden.");
		}

		setStructuralLinks(bestellung, uriInfo);

		// Link-Header setzen
		final Response response = Response.ok(bestellung)
				.links(getTransitionalLinks(bestellung, uriInfo)).build();

		return response;
	}

	public void setStructuralLinks(Bestellung bestellung, UriInfo uriInfo) {
		// URI fuer Kunde setzen
		final AbstractKunde kunde = bestellung.getKunde();
		if (kunde != null) {
			final URI kundeUri = kundeResource.getUriKunde(
					bestellung.getKunde(), uriInfo);
			bestellung.setKundeUri(kundeUri);
		}
	}

	private Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo))
				.rel(SELF_LINK).build();
		return new Link[] {self };
	}

	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById",
				bestellung.getBestellnr(), uriInfo);
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createBestellung(@Valid Bestellung bestellung) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		bestellung = Mock.createBestellung(bestellung);
		return Response.created(getUriBestellung(bestellung, uriInfo)).build();
	}
}
