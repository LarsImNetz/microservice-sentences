package org.lla_private;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.rest.json.mapper.ObjectMapperService;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ITestWriterThreadsafe {

	private IObjectMapperService objectMapperService;
	
	@Before
	public void setUp() {
		Injector inject = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				bind(IObjectMapperService.class).toInstance(new ObjectMapperService());
			}
		});
		objectMapperService = inject.getInstance(IObjectMapperService.class);
	}

	@Test
	public void test() throws Exception {
		for (int i = 0; i < 1000; i++) {
			new Thread(new MyThread(this.objectMapperService));
		}
		Thread.sleep(5 * 1000);
	}

	private static class MyThread implements Runnable {

		private IObjectMapperService objectMapperService;

		public MyThread(IObjectMapperService objectMapperService) {
			this.objectMapperService = objectMapperService;
		}

		@Override
		public void run() {
			List<String> list = Arrays.asList("a", "b");
			objectMapperService.createJsonString(list);
		}
	}

}
