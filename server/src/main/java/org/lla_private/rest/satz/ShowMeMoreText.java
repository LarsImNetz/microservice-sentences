package org.lla_private.rest.satz;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lla_private.service.showmemore.IShowMeMore;

@Path("/satz")
public class ShowMeMoreText {

	private IShowMeMore more;

	@Inject
	public ShowMeMoreText(IShowMeMore more) {
		this.more = more;
	}

	/**
	 * This is a test function, to test if the service can answer.
	 * 
	 * @return a String
	 */
	@GET
	@Path("show-me-more")
	@Produces(MediaType.TEXT_HTML)
	public String test() {
		return more.more();
	}
}
