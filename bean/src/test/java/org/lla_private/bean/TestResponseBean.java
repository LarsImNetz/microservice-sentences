package org.lla_private.bean;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.lla_private.bean.ResponseBean;
import org.skyscreamer.jsonassert.JSONAssert;

public class TestResponseBean {

	@Test
	public void testSerializeBaugeldAntrag() {
		ResponseBean bean = new ResponseBean();
		bean.setSatz("Aber ein A");

		final Serializable original = bean;
		final Serializable copy = SerializationUtils.clone(original);

		Assert.assertEquals(original, copy);

		ResponseBean copyOfBean = (ResponseBean) copy;
		Assert.assertEquals("Aber ein A", copyOfBean.getSatz());
	}

	@Test
	public void testJsonBean() throws Exception {
		ResponseBean bean = new ResponseBean();
		bean.setSatz("Aber ein A");

		final String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean);
		JSONAssert.assertEquals("{'satz': 'Aber ein A'}", json, false);
	}
}
