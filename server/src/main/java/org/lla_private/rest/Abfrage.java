package org.lla_private.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.lla_private.bean.Bean;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.buchstabendreher.IBuchstabenImSatzVerdrehenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/abfrage")
public class Abfrage {

	private static Logger LOGGER = LoggerFactory.getLogger(Abfrage.class);

	private final IObjectMapperService objectMapperService;

	private final IBuchstabenImSatzVerdrehenService satzDreherService;

	@Inject
	public Abfrage(IObjectMapperService objectMapperService, IBuchstabenImSatzVerdrehenService satzDreherService) {
		this.objectMapperService = objectMapperService;
		this.satzDreherService = satzDreherService;
	}

	/**
	 * This is a test function, to test if the service can answer.
	 * @return a String
	 */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_HTML)
	public String test() {
		return "Just a test";
	}

	// TODO: Values per Parameter hineinreichen
	//http://server/abfrage/hello?sentence=Hello%20World
	// sample:
	// http://localhost:8080/sentences-rest-server/abfrage/hello?sentence=Besucheransturm
	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello(@QueryParam("sentence") final String satz) {
		Bean bean = new Bean();
		bean.setSatz(satz);
		LOGGER.debug("getHello() was called with parameter '" + satz + "' and returned a bean");
		return objectMapperService.createJsonString(bean);
	}

	@GET
	@Path("hello2")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello2(@QueryParam("sentence") final String satz) {
		Bean bean = new Bean();
		bean.setSatz(satzDreherService.verdrehen(satz));
		LOGGER.debug("getHello2() was called with parameter '" + satz + "' and returned a bean");
		return objectMapperService.createJsonString(bean);
	}
}
