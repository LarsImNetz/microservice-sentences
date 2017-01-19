package org.lla_private;

import org.lla_private.rest.json.mapper.ObjectMapperModule;
import org.lla_private.service.buchstabendreher.BuchstabenImSatzVerdrehenModule;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new BuchstabenImSatzVerdrehenModule());
		install(new ObjectMapperModule());
		// bind(IObjectMapperService.class).to(ObjectMapperService.class).in(Singleton.class);
	}
	
}