package org.lla_private;

import org.lla_private.rest.json.mapper.ObjectMapperModule;
import org.lla_private.service.RegisterManipulationMethodModule;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new RegisterManipulationMethodModule());
		
		install(new ObjectMapperModule());
	}
	
}