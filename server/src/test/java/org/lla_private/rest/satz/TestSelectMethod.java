package org.lla_private.rest.satz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.IManipulationMethod;
import org.lla_private.service.ManipulationMethods.Assoc;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSelectMethod {
	
	@Mock
	private IObjectMapperService objectMapperService;
	
	@Mock
	private IManipulationMethod manipulationMethods;
	
	@InjectMocks
	private SelectMethod selectMethodSUT;
	

	@Test
	public void testSatzAlgorithmen() {
		Assoc[] assocs = new Assoc[0];
		Mockito.when(manipulationMethods.getMethods()).thenReturn(assocs);
		
		String satzAlgorithmen = selectMethodSUT.satzAlgorithmen();
		Assert.assertNull(satzAlgorithmen);
	}
}
