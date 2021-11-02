package io.petstore.api;

import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PetStoreApiClient {

    private final RequestSpecification spec;
    private final ByteArrayOutputStream requestOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream responseOut = new ByteArrayOutputStream();

    public PetStoreApiClient() {
        spec = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new SwaggerCoverageRestAssured())
                .addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(requestOut)))
                .addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(responseOut)))
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .setBaseUri("https://petstore.swagger.io/v2").build();
    }

    public PetStoreApiClient(final String apiKey) {
        this();
        spec
                .header("api_key", apiKey);
    }

    public Response send(final Request request) {
        return send(request.getDescription(), request);
    }

    @Step("{step}")
    public Response send(final String step, final Request request) {
        final Response response = request.send(RestAssured.given(spec));
        attachTextLogToAllure("request", requestOut);
        attachTextLogToAllure("response", responseOut);
        return response;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = "txt")
    private byte[] attachTextLogToAllure(String name, ByteArrayOutputStream out) {
        return out.toByteArray();
    }
}
