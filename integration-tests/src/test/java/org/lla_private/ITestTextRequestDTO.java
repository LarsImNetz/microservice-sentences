package org.lla_private;

import org.junit.Before;
import org.junit.Test;
import org.lla_private.bean.request.TextRequestDTO;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.rest.json.mapper.ObjectMapperService;

public class ITestTextRequestDTO {

	private IObjectMapperService OBJECTMAPPER;

	@Before
	public void setUp() {
		OBJECTMAPPER = new ObjectMapperService();
	}
	
	@Test
	public void testTextRequestDTO() {
		TextRequestDTO textRequest = new TextRequestDTO();
		
		final String json = OBJECTMAPPER.createJsonString(textRequest);

		System.out.println(json);
	}
}
