package org.lla_private.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.buchstabendreher.IBuchstabenImSatzVerdrehenService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestAbfrage {

	@Mock
	private IBuchstabenImSatzVerdrehenService satzDreherService;
	
	@Mock
	private IObjectMapperService objectMapperService;
	
	@InjectMocks
	private Abfrage abfrageSUT;
	
	@Test
	public void testAbfrageTest() {
		String value = abfrageSUT.test();
		Assert.assertEquals("Just a test", value);
	}
	
	@Test
	public void testAbfrageHello() {
		Mockito.when(objectMapperService.createJsonString(Mockito.any())).thenReturn("a json string");

		String value = abfrageSUT.getHello("satz");
		System.out.println(value);
		Assert.assertEquals("a json string", value);
	}
}
