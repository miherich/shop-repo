package de.shop.kundenverwaltung.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static de.shop.util.Constants.LOADGRAPH;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Produces;

import com.google.common.collect.ImmutableMap;

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
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
			}
	
	public enum OrderType {
		KEINE,
		ID
	}
	
	@Inject
	private transient EntityManager em;
	
	@NotNull(message = KUNDE_NOT_FOUND_ID)
	public AbstractKunde findKundeById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		
		AbstractKunde kunde;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		switch (fetch) {
			case NUR_KUNDE:
				kunde = em.find(AbstractKunde.class, id);
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(AbstractKunde.class, id, props);
				break;
				
			
			default:
				kunde = em.find(AbstractKunde.class, id);
				break;
		}
		
		return kunde;
	}


//		public AbstractKunde findKundeById(Long id) {
//		// TODO Datenbanzugriffsschicht statt Mock
//		return Mock.findKundeById(id); }
	
	
		@NotNull(message = KUNDE_NOT_FOUND_MAIL)
		public AbstractKunde findKundeByEmail(String email) {
			try {
				return em.createNamedQuery(AbstractKunde.FIND_KUNDE_BY_EMAIL, AbstractKunde.class)
						 .setParameter(AbstractKunde.PARAM_KUNDE_EMAIL, email)
						 .getSingleResult();
			}
			catch (NoResultException e) {
				return null;
			}
		
//	public AbstractKunde findKundeByEmail(String email) {
//		if (email == null) {
//			return null;
//		}
		// TODO Datenbanzugriffsschicht statt Mock
		//return Mock.findKundeByEmail(email); }
	}
	
	@NotNull(message = KUNDE_NOT_FOUND)
	public List<AbstractKunde> findAllKunden(FetchType fetch, OrderType order) {
		final TypedQuery<AbstractKunde> query = OrderType.ID.equals(order)
		                                        ? em.createNamedQuery(AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
		                                        		              AbstractKunde.class)
		                                        : em.createNamedQuery(AbstractKunde.FIND_KUNDEN, AbstractKunde.class);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			default:
				break;
		}

		return query.getResultList();
	}
	
//	public List<AbstractKunde> findAllKunden() {
//		// TODO Datenbanzugriffsschicht statt Mock
//		return Mock.findAllKunden();
//	}
	
	@Size(min = 1, message = KUNDE_NOT_FOUND_NACHNAME)
	public List<AbstractKunde> findKundenByNachname(String nachname, FetchType fetch) {
		final TypedQuery<AbstractKunde> query = em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
                                                                    AbstractKunde.class)
                                                  .setParameter(AbstractKunde.PARAM_KUNDE_NACHNAME, nachname);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
				
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;	
							
			default:
				break;
		}
		
		return query.getResultList();
	}
	
//	public List<AbstractKunde> findKundenByNachname(String nachname) {
//		// TODO Datenbanzugriffsschicht statt Mock
//		return Mock.findKundenByNachname(nachname);
//	}

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
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(kunde);
		
		// Gibt es ein anderes Objekt mit gleicher Email-Adresse?
		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getKundennr().longValue() != kunde.getKundennr().longValue()) {
				// anderes Objekt mit gleichem Attributwert fuer email
				throw new EmailExistsException(kunde.getEmail());
			}
		}

		em.merge(kunde);
		return kunde;
	}
	}
//		if (kunde == null) {
//			return null;
//		}
//
////		TODO Kommentare rausnehmen, wenn DB-Zugriffsschicht implementiert
//		// Pruefung, ob die Email-Adresse schon existiert
////		final AbstractKunde vorhandenerKunde = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
////		if (vorhandenerKunde != null) {
//			// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
////			if (vorhandenerKunde.getKundennr() != kunde.getKundennr()) {
////				throw new EmailExistsException(kunde.getEmail());
////			}
////		}
//		
//
//		// TODO Datenbanzugriffsschicht statt Mock
//		Mock.updateKunde(kunde);
//		
//		return kunde;
//	}

