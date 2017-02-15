package org.lla_private;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lla_private.rest.json.mapper.IObjectMapperService;
import org.lla_private.rest.json.mapper.ObjectMapperService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TestJsonDeserialisation {

	private static IObjectMapperService OBJECTMAPPER;

	@BeforeClass
	public static void setUp() {
		OBJECTMAPPER = new ObjectMapperService();
	}

	@Test
	public void testClassDeserializeSimpleDTOFull() {
		final String json = "{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"plz\":12345}";

		final Object obj = OBJECTMAPPER.createObject(json, SimpleDTO.class);
		Assert.assertNotNull(obj);

		final SimpleDTO s = (SimpleDTO) obj;

		Assert.assertEquals(1968, s.getGeburtstag().getYear());
		Assert.assertEquals(1, s.getGeburtstag().getMonthValue());
		Assert.assertEquals(4, s.getGeburtstag().getDayOfMonth());

		Assert.assertEquals("Lars", s.getVorname());
		Assert.assertEquals("Langhans", s.getNachname());

		Assert.assertEquals(null, s.getStrasse());
		Assert.assertEquals(null, s.getHausnummer());
		Assert.assertEquals(Integer.valueOf(12345), s.getPlz());
		Assert.assertEquals(null, s.getOrt());
	}

	@Test
	public void testClassDeserializeSimpleDTOWithTooMuchValues() {
		final String json = "{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"plz\":12345, \"neu\":123}";

		final Object obj = OBJECTMAPPER.createObject(json, SimpleDTO.class);

		// the 'new' Value is too much, we expect an error, a null object
		Assert.assertNull(obj);
	}

	@Test
	public void testDeserializeSimpleDTOWithIgnore() {
		final String json = "{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"plz\":12345, \"neu\":123}";

		final Object obj = OBJECTMAPPER.createObject(json, SimpleDTOWithIgnore.class);
		Assert.assertNotNull(obj);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class SimpleDTOWithIgnore extends SimpleDTO {
	}

	@Test
	public void testClassOtherDeserializeOnlyOne() {
		final String json = "{\"vorname\":\"Lars\"}";

		final Object obj = OBJECTMAPPER.createObject(json, SimpleDTO.class);
		Assert.assertNotNull(obj);

		final SimpleDTO s = (SimpleDTO) obj;

		Assert.assertEquals("Lars", s.getVorname());
		Assert.assertNull(s.getNachname());
	}
}
