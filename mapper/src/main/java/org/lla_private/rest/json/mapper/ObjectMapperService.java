package org.lla_private.rest.json.mapper;

import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Hilfsobjekt, Macht aus einem String ein JSON Objekt 
 *
 */
public class ObjectMapperService implements IObjectMapperService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperService.class);

	private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public ObjectMapperService() {
		// FORMATTER:OFF
		final SimpleModule module = new SimpleModule()
				.addSerializer(LocalDate.class, new LocalDateSerializer())
				.addDeserializer(LocalDate.class,	new LocalDateDeserializer());
		// FORMATTER:ON
		OBJECT_MAPPER.registerModule(module);
	}


	/**
	 * Macht aus einem übergebenen Object ein JSON String
	 * 
	 *  @return json string oder ""
	 */
	public String createJsonString(final Object list) {
		String jsonString = "";
		try {
			jsonString = OBJECT_MAPPER.writer().writeValueAsString(list);
			return jsonString;
		}
		catch (final JsonProcessingException e) {
			LOGGER.warn("Could not serialize bean into JSON string", e);
		}
		return "";
	}

	/**
	 * macht aus einem JSON String und einer Übergebenen Klasse ein neues Objekt
	 * @param json
	 * @param resultClass
	 * @return
	 */
	public <T> Object createObject(final String json, final Class<T> resultClass) {
		try {
			final T result = OBJECT_MAPPER.readValue(json, resultClass);
			return result;
		}
		catch (final JsonMappingException e) {
			LOGGER.error("Error: ", e);
		}
		catch (final JsonParseException e) {
			LOGGER.error("Error: ", e);

		}
		catch (final IOException e) {
			LOGGER.error("Error: ", e);
		}
		return null;
	}
}
