package io.petstore.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {

    private final String description;
    private final RequestSenderOptions requestSenderOptions;

    public Request(final String description,
                   final RequestSenderOptions requestSenderOptions) {
        this.description = description;
        this.requestSenderOptions = requestSenderOptions;
    }

    public String getDescription() {
        return description;
    }

    public Response send(final RequestSpecification spec) {
        return requestSenderOptions.send(spec);
    }
}
