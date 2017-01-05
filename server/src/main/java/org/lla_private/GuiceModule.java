package org.lla_private;

import javax.inject.Singleton;

import org.lla_private.service.satzdreher.ISatzDreherService;
import org.lla_private.service.satzdreher.SatzDreherService;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ISatzDreherService.class).to(SatzDreherService.class).in(Singleton.class);
	}
	
}