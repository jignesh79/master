package com.test.camel.springboot.restdsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.camel.springboot.restdsl.types.PostRequestType;
import com.test.camel.springboot.restdsl.types.ResponseType;

/**
 * End to End test class to bootstrap the application, and choose a random
 * web server port. 
 * @author Jignesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestDslApplicationTest {

	// Spring will inject the random port assigned to the web server
	@LocalServerPort
	int webServerPort;

	@Autowired
	TestRestTemplate testRestTemplate;

	/**
	 * An example integration test (testing all the internal components together).
	 *
	 * Sends a POST request to the REST API, and verifies that the response is valid.
	 * One happy path use case (End to End flow)
	 * @throws Exception
	 */
	@Test
	public void testPostSuccessful() throws Exception {
		// Build up the test request. Create a POJO
		PostRequestType request = new PostRequestType();
		request.setName("Sunset");
		request.setQuantity(2);

		// Send the request. Spring will marshal the 'request' into JSON before sending
		ResponseEntity<ResponseType> response = testRestTemplate.postForEntity(
				"http://localhost:" + webServerPort + "/services/api",
				request,
				ResponseType.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Assert the "message" field in the JSON response 
		assertTrue(response.getBody().getMessage().contains("Thanks for your order"));
	}
	
	/**
	 * An example integration test (testing all the internal components together).
	 *
	 * Sends a POST request to the REST API, and verifies the validation error occurs.
	 * One unhappy path use case (End to End flow)
	 * @throws Exception
	 */
	@Test 
	public void testPostValidationError() throws Exception {
		// Build up the test request. Create a POJO
		PostRequestType request = new PostRequestType();
		request.setName("Sunrise");

		// Send the request. Spring will marshal the 'request' into JSON before sending
		ResponseEntity<ResponseType> response = testRestTemplate.postForEntity(
				"http://localhost:" + webServerPort + "/services/api",
				request,
				ResponseType.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Assert the "message" field in the JSON response 
		assertTrue(response.getBody().getMessage().contains("Invalid request"));
	}
}
