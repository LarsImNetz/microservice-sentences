package org.lla_private.rest.satz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.rest.satz.Abfrage;
import org.lla_private.service.IManipulationMethod;
import org.lla_private.service.IManipulationMethodCaller;
import org.lla_private.service.ManipulationMethods.Assoc;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestAbfrage {

	@Mock
	private IObjectMapperService objectMapperService;
	
	@Mock
	private IManipulationMethod manipulationMethods;
	
	@Mock
	private IManipulationMethodCaller manipulationMethodCaller;
	
	@InjectMocks
	private Abfrage abfrageSUT;
	
	@Test
	public void testSatzAlgorithmen() {
		Assoc[] assocs = new Assoc[0];
		Mockito.when(manipulationMethods.getMethods()).thenReturn(assocs);
		
		String satzAlgorithmen = abfrageSUT.satzAlgorithmen();
		Assert.assertNull(satzAlgorithmen);
	}

	//	@Test
//	public void testAbfrageHello() {
//		Mockito.when(objectMapperService.createJsonString(Mockito.any())).thenReturn("a json string");
//
//		String value = abfrageSUT.satzVerdrehen("satz");
//		System.out.println(value);
//		Assert.assertEquals("a json string", value);
//	}
}
