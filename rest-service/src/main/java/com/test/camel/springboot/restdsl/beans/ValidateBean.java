package com.test.camel.springboot.restdsl.beans;

import java.io.InputStream;
import java.util.Set;

import org.apache.camel.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

/**
 * This class validate front end and back end schema
 * @author Jignesh
 *
 */
@Component
public class ValidateBean {
	private static final Logger log = LoggerFactory.getLogger(ValidateBean.class);
	public static final String FRONT_END_SCHEMA = "myschema-request.json";
	public static final String BACK_END_SCHEMA = "myschema-order.json";

	private ObjectMapper objectMapper = new ObjectMapper();
	private JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);

	/**
	 * Validate the json string using front end schema 
	 * @param jsonString
	 * @return the jsonString
	 * @throws Exception
	 */
	public String validateFront(String jsonString) throws Exception {

		try (InputStream schemaStream = inputStreamFromClasspath(FRONT_END_SCHEMA);) {
			validate(jsonString, schemaStream);
		}
		return jsonString;
	}

	/**
	 * Validate the json string using back end schema
	 * @param jsonString
	 * @return the jsonString
	 * @throws Exception
	 */
	public String validateBack(String jsonString) throws Exception {

		try (InputStream schemaStream = inputStreamFromClasspath(BACK_END_SCHEMA);) {
			validate(jsonString, schemaStream);
		}
		return jsonString;
	}

	/**
	 * Validate the json string using given schema
	 * @param jsonString
	 * @param schemaStream
	 * @return the jsonString
	 * @throws Exception
	 */
	public String validate(String jsonString, InputStream schemaStream) throws Exception {
		log.debug("jsonString- " + jsonString);
		JsonNode json = objectMapper.readTree(jsonString);
		JsonSchema schema = schemaFactory.getSchema(schemaStream);
		Set<ValidationMessage> validationResult = schema.validate(json);

		// print validation errors
		if (validationResult.isEmpty()) {
			log.debug("no validation errors :-)");
		} else {
			validationResult.forEach(vm -> log.debug(vm.getMessage()));
			throw new ValidationException(null, validationResult.toString());
		}
		return jsonString;
	}

	private static InputStream inputStreamFromClasspath(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

}
