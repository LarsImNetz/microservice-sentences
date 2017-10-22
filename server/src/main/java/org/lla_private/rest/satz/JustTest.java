package org.lla_private.rest.satz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/satz")
public class JustTest {

	/**
	 * This is a test function, to test if the service can answer.
	 * 
	 * @return a String
	 */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Just a test";
	}
}
