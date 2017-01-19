package org.lla_private.rest.json.mapper;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

public class ObjectMapperModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IObjectMapperService.class).to(ObjectMapperService.class).in(Singleton.class);
	}	
}
