package org.lla_private;

import java.util.HashSet;
import java.util.Set;
// import java.util.Timer;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.lla_private.rest.satz.Abfrage;
import org.lla_private.rest.satz.JustTest;

import com.google.inject.Guice;

@ApplicationPath("/")
public class RestApplication extends Application {

	@Inject
	public RestApplication(ServiceLocator serviceLocator) {
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(new GuiceModule()));
	}

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();

		classes.add(Abfrage.class);
		classes.add(JustTest.class);
		classes.add(CORSResponseFilter.class);

		return classes;
	}

	// @Override
	// public Set<Object> getSingletons() {
	// final Set<Object> singletons = new HashSet<>();
	//
	// singletons.add(new SatzDreherModule());
	// return singletons;
	// }
}
