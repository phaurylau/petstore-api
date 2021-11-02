package io.petstore.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface RequestSenderOptions {

    Response send(final RequestSpecification requestSpecification);
}
