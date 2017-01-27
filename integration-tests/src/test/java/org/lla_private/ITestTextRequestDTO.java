package org.lla_private;

import org.junit.Before;
import org.junit.Test;
import org.lla_private.bean.request.TextRequestDTO;

public class ITestTextRequestDTO {

	private ObjectMapperHelper OBJECTMAPPER;

	@Before
	public void setUp() {
		OBJECTMAPPER = new ObjectMapperHelper();
	}
	
	@Test
	public void testTextRequestDTO() {
		TextRequestDTO textRequest = new TextRequestDTO();
		
		final String json = OBJECTMAPPER.createJsonString(textRequest);

		System.out.println(json);
	}
}
