package com.test.camel.springboot.restdsl;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Unit Test to test the camel route
 * @author Jignesh
 *
 */
public class RestDslRouteBuilderTest extends CamelTestSupport {

    // override this method with the routes to be tested
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        // override this method and provide our RouteBuilder class
        return new RestDslRouteBuilder();
    }

    // write a simple JUnit test case
    @Test 
    public void testRouteSuccessful() throws Exception {
        // 1. Arrange.. get the mock endpoint and set an assertion
        MockEndpoint mockOutput = getMockEndpoint("mock:output");
        mockOutput.expectedMessageCount(1);

        // 2. Act.. send a message to the start component
    	String jsonInput= "{\"name\": \"Sunrise\",\"quantity\": 2}";
        template.sendBody("direct:remote", jsonInput);

        // 3. Assert.. verify that the mock component received 1 message
        assertMockEndpointsSatisfied();
    }

    // write a simple JUnit test case for validation error
    @Test 
    public void testRouteFailWithInvalidQuantityField() throws Exception {
        // 1. Arrange.. get the mock endpoint and set an assertion
        MockEndpoint mockOutput = getMockEndpoint("mock:validationFailed");
        mockOutput.expectedMessageCount(1);

        // 2. Act.. send a message to the start component
    	String jsonInput= "{\"name\": \"Sunset\",\"quantity\":  \"abc\"}";
        template.sendBody("direct:remote", jsonInput);

        // 3. Assert.. verify that the mock component received 1 message
        assertMockEndpointsSatisfied();
    }

    // write a simple JUnit test case for validation error
    @Test
    public void testRouteFailWithMissingMandatoryField() throws Exception {
        // 1. Arrange.. get the mock endpoint and set an assertion
        MockEndpoint mockOutput = getMockEndpoint("mock:validationFailed");
        mockOutput.expectedMessageCount(1);

        // 2. Act.. send a message to the start component
    	String jsonInput= "{\"name\": \"Sunset\"}";
        template.sendBody("direct:remote", jsonInput);

        // 3. Assert.. verify that the mock component received 1 message
        assertMockEndpointsSatisfied();
    }

}