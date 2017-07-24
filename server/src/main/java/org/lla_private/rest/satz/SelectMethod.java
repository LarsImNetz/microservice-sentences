package org.lla_private.rest.satz;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.IManipulationMethod;
import org.lla_private.service.ManipulationMethods.Assoc;

@Path("/satz")
public class SelectMethod {
	
	private final IObjectMapperService objectMapperService;

	private final IManipulationMethod manipulationMethod;

	@Inject
	public SelectMethod(IObjectMapperService objectMapperService, IManipulationMethod manipulationMethod) {
		this.objectMapperService = objectMapperService;
		this.manipulationMethod = manipulationMethod;
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
