package com.test.camel.springboot.restdsl;

import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.test.camel.springboot.restdsl.beans.FailureProcessor;
import com.test.camel.springboot.restdsl.beans.MockService;
import com.test.camel.springboot.restdsl.beans.Order;
import com.test.camel.springboot.restdsl.beans.PostBean;
import com.test.camel.springboot.restdsl.beans.Transformer;
import com.test.camel.springboot.restdsl.beans.ValidateBean;
import com.test.camel.springboot.restdsl.types.PostRequestType;
import com.test.camel.springboot.restdsl.types.ResponseType;

/**
 * This RouteBuilder defines REST API using Camel's REST DSL.
 *
 * 
 */
@Component
public class RestDslRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// This section tells Camel how to configure the REST service
		restConfiguration().component("servlet")
				// Allow Camel to try to marshal/unmarshal between Java objects and JSON
				.bindingMode(RestBindingMode.auto);

		// Now define the REST API
		rest().path("/api") // This makes the API available at http://host:port/$CONTEXT_ROOT/api
				.consumes("application/json").produces("application/json")

				// HTTP: POST /api
				.post().type(PostRequestType.class).outType(ResponseType.class).route().marshal()
				.json(JsonLibrary.Jackson).to("direct:remote");

		// Exception handler
		onException(ValidationException.class).process(new FailureProcessor()).bean(PostBean.class, "invalid")
				.to("mock:validationFailed").handled(true);

		from("direct:remote")
				// validate using front-end schema
				.bean(method(ValidateBean.class, "validateFront"))
//		        .to("json-validator:myschema-request.json")
				.unmarshal().json(JsonLibrary.Jackson, PostRequestType.class)
				// transform a message
				.bean(Transformer.class)
				// invoke mock service
				.bean(MockService.class)
				.marshal().json(JsonLibrary.Jackson)
				// validate using back-end schema
				.bean(method(ValidateBean.class, "validateBack"))
//		        .to("json-validator:myschema-order.json")
				.unmarshal().json(JsonLibrary.Jackson, Order.class)				
				.bean(PostBean.class, "response")
				.to("mock:output");
	}
}
