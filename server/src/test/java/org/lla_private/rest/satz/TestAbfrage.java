package org.lla_private.rest.satz;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.bean.request.TextRequestDTO;
import org.lla_private.bean.request.TextRequestDTO.Sentence;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.service.IManipulationMethodCaller;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestAbfrage {

	@Mock
	private IObjectMapperService objectMapperService;

	@Mock
	private IManipulationMethodCaller manipulationMethodCaller;

	@InjectMocks
	private Abfrage abfrageSUT;

	@Test
	public void testManipulation() throws IllegalArgumentException, InterruptedException {
		// TODO: Test berichtigen
		//	Mockito.when(objectMapperService.createJsonString(Mockito.any())).thenReturn("a json string");
		TextRequestDTO mock = Mockito.mock(TextRequestDTO.class);
		Mockito.when(objectMapperService.createObject(Mockito.anyString(), Mockito.any())).thenReturn(mock);

		Sentence sentence = Mockito.mock(Sentence.class);
		Mockito.when(sentence.getSentence()).thenReturn("satz");
		Mockito.when(mock.getSentence()).thenReturn(sentence);

		String json = "json String";
		// TODO: fertig machen!
		//		Response response = abfrageSUT.satzManipulieren(json);

		//	System.out.println(value);
//		Assert.assertEquals(200, response.getStatus());

	}
}
