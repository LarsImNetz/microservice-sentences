package org.lla_private;

import org.lla_private.rest.json.mapper.ObjectMapperModule;
import org.lla_private.service.buchstabendreher.BuchstabenImSatzVerdrehenModule;
import org.lla_private.service.kyrillisch.BuchstabenImSatzKyrillischModule;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO: Die SentenceServices müssen hier weg!
		install(new BuchstabenImSatzVerdrehenModule());
		install(new BuchstabenImSatzKyrillischModule());
		
		install(new ObjectMapperModule());
		// bind(IObjectMapperService.class).to(ObjectMapperService.class).in(Singleton.class);
	}
	
}