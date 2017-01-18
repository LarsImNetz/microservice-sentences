package org.lla_private.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hilfsobjekt, Macht aus einem String ein JSON Objekt 
 *
 */
public class ObjectMapperService implements IObjectMapperService {

	private static Logger LOGGER = LoggerFactory.getLogger(ObjectMapperService.class);

	private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * Macht aus einem übergebenen Object ein JSON String
	 * 
	 *  @return json string oder ""
	 */
	public String createJsonString(final Object list) {
	//TODO: Irgendwo habe ich auch das gegenstück, muss hier mit rein 
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
}
