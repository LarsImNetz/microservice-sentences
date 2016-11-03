package org.lla_private;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.lla_private.bean.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/abfrage")
public class Abfrage {

	private static Logger LOGGER = LoggerFactory.getLogger(Abfrage.class);

	// TODO: OBJECT_MAPPER von aussen injecten
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	// TODO: Values per Parameter hineinreichen
	//http://server/abfrage/hello
	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello(@QueryParam("sentence") final String satz) {
		Bean bean = new Bean();
		bean.setSatz(satz);
		LOGGER.debug("getHello() was called with parameter '" +satz+ "' and returned a bean");
		return createJsonString(bean);
	}

	private String createJsonString(final Object list) {
		String jsonString = "";
		try {
			jsonString = OBJECT_MAPPER.writer().writeValueAsString(list);
		}
		catch (final Exception e) {
			LOGGER.warn("Could not serialize bean into JSON string", e);
			return "";
		}

		return jsonString;
	}

}
