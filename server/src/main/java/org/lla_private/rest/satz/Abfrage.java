package org.lla_private.rest.satz;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

	private final IManipulationMethodCaller manipulationMethodCaller;
	
	@Inject
	public Abfrage(IObjectMapperService objectMapperService, IManipulationMethodCaller manipulationMethodCaller) {
		this.objectMapperService = objectMapperService;
		this.manipulationMethodCaller = manipulationMethodCaller;
	}

	// TODO: Umbau, das ein AbfrageJson entgegen genommen wird
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
			LOGGER.info("Der Satz: %s", satz);
		}
		else {
			LOGGER.warn("Konnte den Satz nicht aus den Parametern extrahieren.");
		}
		
		if (satz == null || satz.isEmpty()) {
			LOGGER.warn("Der Satz scheint leer oder nicht vorhanden!");
			throw new IllegalArgumentException("Bitte trage etwas ein...");
		}
		String convertMethod = textRequest.getSentence().getSentenceMethod();
		LOGGER.info("Die convert Methode: %s", convertMethod);
		
		satz = manipulationMethodCaller.callAlgorithm(convertMethod, satz);		
		LOGGER.info("Der übersetzte Satz: %s", satz);
		return satz;
	}


}
