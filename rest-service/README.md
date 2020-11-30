# spring-boot-rest-service

This is an application to create a Sample REST API using Camel and Spring Boot for below requirements.

It uses Camel's  _servlet_ component to provide the REST service, which runs in the embedded web server, which is running on port 8080.


# Requirements:
* Expose a Restful Service
* HTTP verb as POST and Media Type can be JSON or XML
* Define/Create a front end and backend schemaâ€™s (JSON or XML Schemaâ€™s) â€“ A simple schema should be sufficient.
* Validate a Message (using aÂ front end JSON or XML Schema)
* Transform a Message (Front end JSON or XML format to a backend JSON or XML format)
* Validate a transformed message (using a backend JSON or XML Schema)
* Build a Mock Service to receive a message and return some sample response
* Unit Test
* Created a test that connects to the API you have created, through any Rest Client, and returns a response
* One happy path use case (End to End flow)
* One unhappy path use case (End to End flow)
* Once the exercise is completed then it would be great to create a Docker file to create an image of the microservice. (Itâ€™s optional, but preferred)

### Pre-requisites
Install JDK8
Intall Maven
Install Docker

## To run

To run the app:

    mvn clean spring-boot:run

Then to test the REST service's POST operation - see how it returns a JSON object:

    $ curl --request POST \
        --data "{ \"name\": \"Sunset\", \"quantity\" : 12 }" \
        --header "Content-Type: application/json" \
        http://localhost:8080/services/api/
    {"message":"Thanks for your order of Sunset!"}

## Running  tests
mvn clean test

