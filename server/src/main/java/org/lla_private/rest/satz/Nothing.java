package org.lla_private.rest.satz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.lla_private.bean.ResponseBean;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/satz")
public class Nothing {

	private static Logger LOGGER = LoggerFactory.getLogger(Nothing.class);

	private final IObjectMapperService objectMapperService;

	public Nothing(IObjectMapperService objectMapperService) {
	 this.objectMapperService = objectMapperService;
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
		LOGGER.debug("satzDoNothing() was called with parameter '%s' and returned a bean", satz);
		return objectMapperService.createJsonString(bean);
	}


}
