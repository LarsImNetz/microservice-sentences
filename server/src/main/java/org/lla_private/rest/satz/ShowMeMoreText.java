package org.lla_private.rest.satz;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lla_private.service.showmemore.IShowMeMoreService;

@Path("/satz")
public class ShowMeMoreText {

	private IShowMeMoreService more;

	@Inject
	public ShowMeMoreText(IShowMeMoreService more) {
		this.more = more;
	}

	/**
	 * This is a function, to send a test sentence with all characters.
	 * 
	 * @return a String
	 */
	@GET
	@Path("show-me-more")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return more.more();
	}
}
