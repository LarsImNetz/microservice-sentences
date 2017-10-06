package org.lla_private;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PreMatching
public class LogEveryRequestFilter implements ContainerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogEveryRequestFilter.class);

	@Override
	public void filter(final ContainerRequestContext requestContext) throws IOException {

		LOGGER.info("user-agent: " + requestContext.getHeaderString("user-agent"));
		LOGGER.info(
				"uri path: /rest-api/" + requestContext.getUriInfo()
						.getPath());
		final MultivaluedMap<String, String> pathParemeters = requestContext.getUriInfo()
				.getQueryParameters();
		for (final Map.Entry<String, List<String>> e : pathParemeters.entrySet()) {
			for (final String v : e.getValue()) {
				LOGGER.info("Param: " + e.getKey() + "=" + v);
			}
		}
	}
}
