package org.lla_private.rest.json.mapper;


public interface IObjectMapperService {
	/**
	 * Create a String in JSON format out of the given object
	 * @param list
	 * @return
	 */
	String createJsonString(final Object aObject);

	/**
	 * Create an object of resultClass type with content of the json.
	 * @param json formatted string
	 * @param resultClass
	 * @return resultClass type object with json content or NULL
	 */
	<T> Object createObject(final String json, final Class<T> resultClass);
}
