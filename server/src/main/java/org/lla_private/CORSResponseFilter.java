package org.lla_private;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.lla_private.base.Environment;


final class CORSResponseFilter implements ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		final Environment currentEnvironment = Environment.get();
		if (currentEnvironment == Environment.CORS) {
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization, X-Requested-With, X-Auth-Token, Accept");
			headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		}
	}
}
