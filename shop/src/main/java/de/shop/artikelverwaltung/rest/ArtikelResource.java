package de.shop.artikelverwaltung.rest;

import static de.shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;



//import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
//import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;




//import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteil;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.artikelverwaltung.service.ArtikelService;
import de.shop.util.interceptor.Log;
import de.shop.util.rest.UriHelper;

@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75",
		TEXT_XML + ";qs=0.5" })
@Consumes
@Log
@RequestScoped
public class ArtikelResource {
	
	public static final String ARTIKEL_ID_PATH_PARAM = "id";
	
	public static final String ARTIKEL_NOT_FOUND = "artikel.notFound.all";
	public static final String ARTIKEL_NOT_FOUND_ID = "artikel.notFound.id";
	
	@Context
	private UriInfo uriInfo;

//	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
//	private static final String NOT_FOUND_ID = "artikel.notFound.id";

	
	 @Inject
	 private ArtikelService as;

	@Inject
	private UriHelper uriHelper;

	// @PreDestroy
	// private void preDestroy() {
	// LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	// }

	@GET
	public Response findAllArtikel() {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final List<AbstractArtikel> artikelList = as.findAllArtikel();
			return Response.ok(
				new GenericEntity<List<? extends AbstractArtikel>>(artikelList) {
				}).build();
	}

	@GET
	@Path("{"+ARTIKEL_ID_PATH_PARAM+":[1-9][0-9]*}")
	public Response findArtikelById(@PathParam(ARTIKEL_ID_PATH_PARAM) int id) {
		final AbstractArtikel artikel = as.findArtikelById(id);
		
			return Response.ok(artikel)
				.links(getTransitionalLinks(artikel, uriInfo)).build();
	}

	private Link[] getTransitionalLinks(AbstractArtikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
				.rel(SELF_LINK).build();

		return new Link[] {self };
		}

	public URI getUriArtikel(AbstractArtikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelById",
				artikel.getArtikelNr(), uriInfo);
	}

	@POST
	@Path("/fahrrad")
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createFahrrad(@Valid Fahrrad fahrrad) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		fahrrad = as.createFahrrad(fahrrad);
		return Response.created(getUriArtikel(fahrrad, uriInfo)).build();
	}

	@POST
	@Path("/zubehoer")
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createZubehoer(@Valid Zubehoer zubehoer) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		zubehoer = as.createZubehoer(zubehoer);
		return Response.created(getUriArtikel(zubehoer, uriInfo)).build();
	}

	@POST
	@Path("/ersatzteil")
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createErsatzteil(@Valid Ersatzteil ersatzteil) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		ersatzteil = as.createErsatzteil(ersatzteil);
		return Response.created(getUriArtikel(ersatzteil, uriInfo)).build();
	}

	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(@Valid AbstractArtikel artikel) {
		as.updateArtikel(artikel);
	}

}
