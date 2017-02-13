package org.lla_private.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.lla_private.bean.ResponseBean;
import org.lla_private.bean.request.TextRequestDTO;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.IManipulationMethod;
import org.lla_private.service.IManipulationMethodCaller;
import org.lla_private.service.ManipulationMethods.Assoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/satz")
public class Abfrage {

	private static Logger LOGGER = LoggerFactory.getLogger(Abfrage.class);

	private final IObjectMapperService objectMapperService;

	private final IManipulationMethod manipulationMethod;

	private final IManipulationMethodCaller manipulationMethodCaller;
	
	@Inject
	public Abfrage(IObjectMapperService objectMapperService, 
			 IManipulationMethod manipulationMethod, IManipulationMethodCaller manipulationMethodCaller) {
		this.objectMapperService = objectMapperService;
		this.manipulationMethod = manipulationMethod;
		this.manipulationMethodCaller = manipulationMethodCaller;
	}

	/**
	 * This is a test function, to test if the service can answer.
	 * 
	 * @return a String
	 */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_HTML)
	public String test() {
		return "Just a test";
	}

	/**
	 * Satz manipulieren nothing()
	 * 
	 * Der übergebene Satz wird nur zurückgegeben und nicht manipuliert
	 * http://server/satz/nothing?sentence=Hello%20World
	 * 
	 * sample:
	 * http://localhost:8080/sentences-rest-server/satz/nothing?sentence=Besucheransturm
	 *
	 * @param satz
	 *            als Parameter ?sentence=<satz>
	 * @return json String {satz: "<satz>"}
	 */
	//
	@GET
	@Path("nothing")
	@Produces(MediaType.APPLICATION_JSON)
	public String satzDoNothing(@QueryParam("sentence") final String satz) {
		ResponseBean bean = new ResponseBean();
		bean.setSatz(satz);
		LOGGER.debug("satzDoNothing() was called with parameter '" + satz + "' and returned a bean");
		return objectMapperService.createJsonString(bean);
	}

	/**
	 * Satz manipulieren nothing()
	 * 
	 * Der übergebene Satz wird mit dem BuchstabenImSatzVerdrehenService
	 * manipuliert http://server/satz/nothing?sentence=Hello%20World
	 * 
	 * sample:
	 * http://localhost:8080/sentences-rest-server/satz/nothing?sentence=Besucheransturm
	 *
	 * @param satz
	 *            als Parameter ?sentence=<satz>
	 * @return json String {satz: "<satz>"}
	 */
	//
//	@GET
//	@Path("verdrehen")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String satzVerdrehen(@QueryParam("sentence") final String satz) {
//		ResponseBean bean = new ResponseBean();
//		bean.setSatz(satzDreherService.verdrehen(satz));
//		LOGGER.debug("satzVerdrehen() was called with parameter '" + satz + "' and returned a bean");
//		return objectMapperService.createJsonString(bean);
//	}

	@POST
	@Path("manipulate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response satzManipulieren(String json) throws InterruptedException, IllegalArgumentException {
		try {
			LOGGER.info("neue Anfrage: manipulate called");
			String satz = manipulateSatz(json);
			Thread.sleep(1000);
			// TODO: Das '\n' ist ein illegales Zeichen?
			satz = satz.replaceAll("\n", " ");
			
			String result = "{\"text\":\"" + satz + "\"}";
			return Response.ok().entity(result).build();
		}
		catch (IllegalArgumentException e) {
			LOGGER.error("Illegales Argument: ", e);
			String result = "{\"message\":\"" + e.getMessage() + "\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
		}
	}

	private String manipulateSatz(String json) {
		LOGGER.info(json);

		String satz = "Hallo angulars";
		TextRequestDTO textRequest = null;

		Object obj = objectMapperService.createObject(json, TextRequestDTO.class);
		if (obj instanceof TextRequestDTO) {
			textRequest = (TextRequestDTO) obj;
			satz = textRequest.getSentence().getSentence();
			LOGGER.info("Der Satz: " + satz);
		}
		else {
			LOGGER.warn("Konnte den Satz nicht aus den Parametern extrahieren.");
		}
		
		if (satz == null || satz.isEmpty()) {
			LOGGER.warn("Der Satz scheint leer oder nicht vorhanden!");
			throw new IllegalArgumentException("Bitte trage etwas ein...");
		}
		String convertMethod = textRequest.getSentence().getSentenceMethod();
		LOGGER.info("Die convert Methode: " + convertMethod);
		
		satz = manipulationMethodCaller.callAlgorithm(convertMethod, satz);		
		LOGGER.info("Der übersetzte Satz: " + satz);
		return satz;
	}

	/* HINT: @POST oder @GET ist hier Pflicht! */
	@GET
	@Path("select")
	@Produces(MediaType.APPLICATION_JSON)
	public String satzAlgorithmen() {
		final Assoc[] items = manipulationMethod.getMethods();
		return objectMapperService.createJsonString(items);
	}
}


/* TODO: Testsatz:
 * Jetzt bin ich in der Lage auch die Zahlen mit diesem hübschen Struck-Font aus HTML5 darzustellen. Die Zeichen sehen doch nett aus.
* 1234567890
 */
