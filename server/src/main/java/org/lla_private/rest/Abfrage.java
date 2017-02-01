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
import org.lla_private.service.ManipulationMethods.Assoc;
import org.lla_private.service.buchstabendreher.IBuchstabenImSatzVerdrehenService;
import org.lla_private.service.kyrillisch.IBuchstabenImSatzKyrillischService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/satz")
public class Abfrage {

	private static Logger LOGGER = LoggerFactory.getLogger(Abfrage.class);

	private final IObjectMapperService objectMapperService;

	private final IBuchstabenImSatzVerdrehenService satzDreherService;

	private final IBuchstabenImSatzKyrillischService satzKyrillischService;

	private final IManipulationMethod manipulationMethod;

	@Inject
	public Abfrage(IObjectMapperService objectMapperService, IBuchstabenImSatzVerdrehenService satzDreherService,
			IBuchstabenImSatzKyrillischService kyrillischService, IManipulationMethod manipulationMethod) {
		this.objectMapperService = objectMapperService;
		this.satzDreherService = satzDreherService;
		this.satzKyrillischService = kyrillischService;
		this.manipulationMethod = manipulationMethod;
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
	@GET
	@Path("verdrehen")
	@Produces(MediaType.APPLICATION_JSON)
	public String satzVerdrehen(@QueryParam("sentence") final String satz) {
		ResponseBean bean = new ResponseBean();
		bean.setSatz(satzDreherService.verdrehen(satz));
		LOGGER.debug("satzVerdrehen() was called with parameter '" + satz + "' and returned a bean");
		return objectMapperService.createJsonString(bean);
	}

	@POST
	@Path("manipulate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response satzManipulieren(String json) throws InterruptedException, IllegalArgumentException {
		try {
			String satz = manipulateSatz(json);
			Thread.sleep(1000);
			String result = "{\"text\":\"" + satz + "\"}";
			return Response.ok().entity(result).build();
		} catch (IllegalArgumentException e) {
			String result = "{\"message\":\"" + e.getMessage() + "\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
		}
	}

	private String manipulateSatz(String json) {
		System.out.println(json);

		String satz = "Hallo angulars";
		TextRequestDTO textRequest = null;

		Object obj = objectMapperService.createObject(json, TextRequestDTO.class);
		if (obj instanceof TextRequestDTO) {
			textRequest = (TextRequestDTO) obj;
			satz = textRequest.getSentence().getSentence();
		}

		if (satz == null || satz.isEmpty()) {
			throw new IllegalArgumentException("Bitte trage etwas ein...");
		}
		String convertMethod = textRequest.getSentence().getSentenceMethod();
		switch (convertMethod) {
		case "verdrehen":
			return satzDreherService.verdrehen(satz);
		case "kyrillisch":
			return satzKyrillischService.convert(satz);
		default:
			break;
		}

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
