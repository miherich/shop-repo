package de.shop.kundenverwaltung.rest;

import static de.shop.util.Constants.ADD_LINK;
import static de.shop.util.Constants.FIRST_LINK;
import static de.shop.util.Constants.LAST_LINK;
import static de.shop.util.Constants.SELF_LINK;
import static de.shop.util.Constants.UPDATE_LINK;
import static de.shop.util.Constants.KEINE_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.Email;

import com.google.common.base.Strings;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.rest.BestellungResource;
import de.shop.bestellverwaltung.service.BestellungServiceImpl;
//import de.shop.bestellverwaltung.service.BestellungService;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.service.KundeService;
import de.shop.kundenverwaltung.service.KundeService.FetchType;
import de.shop.kundenverwaltung.service.KundeService.OrderType;
import de.shop.util.interceptor.Log;
import de.shop.util.rest.UriHelper;

@Path("/kunden")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
@Log
public class KundeResource {
	public static final String KUNDEN_ID_PATH_PARAM = "id";
	public static final String KUNDEN_NACHNAME_QUERY_PARAM = "nachname";
	public static final String KUNDEN_EMAIL_QUERY_PARAM = "email";
	
	public static final String KUNDEN_NACHNAME_PATTERN = "{kundenverwaltung.kunde.nachname.pattern}";
	public static final String KUNDEN_EMAIL_PATTERN = "{kundenverwaltung.kunde.email.pattern}";
	public static final String KUNDEN_ID_NOT_FOUND = "{kundenverwaltung.kunde.notFound.id}";

	@Inject
	private KundeService ks;

	@Context
	private UriInfo uriInfo;

	@Inject
	private BestellungResource bestellungResource;

	@Inject
	private BestellungServiceImpl bs;
	
	@Inject
	private UriHelper uriHelper;

	@GET
	@Produces({ TEXT_PLAIN, APPLICATION_JSON })
	@Path("version")
	public String getVersion() {
		return "1.0";
	}

	@GET
	@Path("{" + KUNDEN_ID_PATH_PARAM + ":[1-9][0-9]*}")
	public Response findKundeById(@PathParam(KUNDEN_ID_PATH_PARAM) Long id) {
		final AbstractKunde kunde = ks.findKundeById(id, FetchType.NUR_KUNDE);
		return Response.ok(kunde).links(getTransitionalLinks(kunde, uriInfo))
				.build();
	}

	public void setStructuralLinks(AbstractKunde kunde, UriInfo uriInfo) {
		// URI fuer Bestellungen setzen
		final URI uri = getUriBestellungen(kunde, uriInfo);
		kunde.setBestellURI(uri);
	}

	private URI getUriBestellungen(AbstractKunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class,
				"findBestellungenByKundeId", kunde.getKundennr(), uriInfo);
	}

	public Link[] getTransitionalLinks(AbstractKunde kunde, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
				.rel(SELF_LINK).build();

		final Link add = Link
				.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
				.rel(ADD_LINK).build();

		final Link update = Link
				.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
				.rel(UPDATE_LINK).build();

		return new Link[] {self, add, update };
	}

	public URI getUriKunde(AbstractKunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findKundeById",
				kunde.getKundennr(), uriInfo);
	}

	@GET
	public Response findKunden(
			@QueryParam(KUNDEN_NACHNAME_QUERY_PARAM) 
			@Pattern(regexp = AbstractKunde.NACHNAME_PATTERN, message = KUNDEN_NACHNAME_PATTERN) 
			String nachname,
			@QueryParam(KUNDEN_EMAIL_QUERY_PARAM) @Email(message = KUNDEN_EMAIL_PATTERN) String email) {
		List<? extends AbstractKunde> kunden = null;
		AbstractKunde kunde = null;
		if (Strings.isNullOrEmpty(nachname) && Strings.isNullOrEmpty(email)) {
			kunden = ks.findAllKunden(FetchType.NUR_KUNDE, OrderType.ID);
		}
		else if (Strings.isNullOrEmpty(email)) {
			kunden = ks.findKundenByNachname(nachname, FetchType.NUR_KUNDE);
		}
		else {
			kunde = ks.findKundeByEmail(email);
		}

		Object entity = null;
		Link[] links = null;
		if (kunden != null) {
			for (AbstractKunde k : kunden) {
				setStructuralLinks(k, uriInfo);
			}

			entity = new GenericEntity<List<? extends AbstractKunde>>(kunden) {
			};
			links = getTransitionalLinksKunden(kunden, uriInfo);
		} 
		else if (kunde != null) {
			entity = kunde;
			links = getTransitionalLinks(kunde, uriInfo);
		}

		return Response.ok(entity).links(links).build();
	}

	private Link[] getTransitionalLinksKunden(
			List<? extends AbstractKunde> kunden, UriInfo uriInfo) {
		if (kunden == null || kunden.isEmpty()) {
			return null;
		}

		final Link first = Link.fromUri(getUriKunde(kunden.get(0), uriInfo))
				.rel(FIRST_LINK).build();
		final int lastPos = kunden.size() - 1;
		final Link last = Link
				.fromUri(getUriKunde(kunden.get(lastPos), uriInfo))
				.rel(LAST_LINK).build();

		return new Link[] {first, last };
	}

	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
		public Response findBestellungenByKundeId(
			@PathParam(KUNDEN_ID_PATH_PARAM) Long kundeId) {
final AbstractKunde kunde = ks.findKundeById(kundeId, FetchType.MIT_BESTELLUNGEN);
		
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		// URIs innerhalb der gefundenen Bestellungen anpassen
		if (bestellungen != null) {
			for (Bestellung bestellung : bestellungen) {
				bestellungResource.setStructuralLinks(bestellung, uriInfo);
			}
			// FIXME JDK 8 hat Lambda-Ausdruecke
			//bestellungen.parallelStream()
			//            .forEach(b -> bestellungResource.setStructuralLinks(b, uriInfo));
		}
		
		final Response response = Response.ok(new GenericEntity<List<Bestellung>>(bestellungen) { })
                                          .links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
                                          .build();
		return response;
	}

	private Link[] getTransitionalLinksBestellungen(
			List<Bestellung> bestellungen, AbstractKunde kunde, UriInfo uriInfo) {
		if (bestellungen == null || bestellungen.isEmpty()) {
			return new Link[0];
		}

		final Link self = Link.fromUri(getUriBestellungen(kunde, uriInfo))
				.rel(SELF_LINK).build();

		final Link first = Link
				.fromUri(
						bestellungResource.getUriBestellung(
								bestellungen.get(0), uriInfo)).rel(FIRST_LINK)
				.build();
		final int lastPos = bestellungen.size() - 1;

		final Link last = Link
				.fromUri(
						bestellungResource.getUriBestellung(
								bestellungen.get(lastPos), uriInfo))
				.rel(LAST_LINK).build();

		return new Link[] {self, first, last };
	}

	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
    @Transactional
	public Response createKunde(@Valid AbstractKunde kunde) {
		kunde.setKundennr(KEINE_ID);
		kunde = ks.createKunde(kunde);
		return Response.created(getUriKunde(kunde, uriInfo)).build();
	}

	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
    @Transactional
	public void updateKunde(@Valid AbstractKunde kunde) {
		ks.updateKunde(kunde);
	}
}
