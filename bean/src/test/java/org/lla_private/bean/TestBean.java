package org.lla_private.bean;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.lla_private.bean.Bean;
import org.skyscreamer.jsonassert.JSONAssert;

public class TestBean {

	@Test
	public void testSerializeBaugeldAntrag() {
		Bean bean = new Bean();
		bean.setSatz("Aber ein A");

		final Serializable original = bean;
		final Serializable copy = SerializationUtils.clone(original);

		Assert.assertEquals(original, copy);

		Bean copyOfBean = (Bean) copy;
		Assert.assertEquals("Aebr ein A", copyOfBean.getSatz());
	}

	@Test
	public void testJsonBean() throws Exception {
		Bean bean = new Bean();
		bean.setSatz("Aber ein A");

		final String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean);
		JSONAssert.assertEquals("{'satz': 'Aebr ein A'}", json, false);
	}
}
