package org.lla_private.rest.satz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.rest.satz.Nothing;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestNothing {
	
	@Mock
	private IObjectMapperService objectMapperService;
	
	private Nothing nothingSUT;

	
	@Before
	public void setUp() {
		nothingSUT = new Nothing(objectMapperService);
	}


	@Test
	public void testSatzDoNothing() {
		// Weil wir am ende der Funktion den objectMapperService verwenden, müssen wir diesen Mocken
		Mockito.when(objectMapperService.createJsonString(Mockito.any())).thenReturn("a json string");

		String satz = "Dies ist ein Satz";
		String response = nothingSUT.satzDoNothing(satz);
		
		Assert.assertNotNull(response);
		Assert.assertEquals("a json string", response);
	}

}
