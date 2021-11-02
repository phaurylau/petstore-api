package io.petstore.tests;

import io.petstore.api.model.store.OrderRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.petstore.api.requests.StoreService.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Feature("store")
@DisplayName("Access to Petstore orders")
public class StoreTests extends APITest {

    @DisplayName("Place an order for a pet")
    @Story("POST")
    @Tag("Smoke")
    @Test
    public void test00() {
        final OrderRequest orderRequest = makeOrderRequest();
        apiClient.send(postCreateOrder(orderRequest)).then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/order-schema.json"))
                .body("id", equalTo(orderRequest.getId()));
    }

    @DisplayName("Find purchase order by ID")
    @Story("GET")
    @Test
    public void test01() {
        final OrderRequest orderRequest = makeOrderRequest();
        apiClient.send(postCreateOrder(orderRequest));
        apiClient.send(getOrderById(orderRequest.getId())).then().statusCode(200);
    }

    @DisplayName("Find purchase order by invalid ID")
    @Story("GET")
    @Test
    public void test02() {
        apiClient.send(getOrderById(-9999)).then().statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"));
    }

    @DisplayName("Delete purchase order by ID")
    @Story("DELETE")
    @Test
    public void test03() {
        final OrderRequest orderRequest = makeOrderRequest();
        apiClient.send(postCreateOrder(orderRequest));
        apiClient.send(deleteOrderById(orderRequest.getId())).then().statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));
    }

    @DisplayName("Delete purchase order by invalid ID")
    @Story("DELETE")
    @Test
    public void test04() {
        apiClient.send(deleteOrderById(432432423)).then().statusCode(404)
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("Order not found"));
    }

    @DisplayName("Returns pet inventories by status")
    @Story("GET")
    @Test
    public void test05() {
        apiClient.send(getInventory()).then().statusCode(200);
    }

    public OrderRequest makeOrderRequest() {
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(1);
        orderRequest.setPetId(1);
        orderRequest.setQuantity(2);
        orderRequest.setShipDate("2021-10-31T09:16:00.602Z");
        orderRequest.setStatus("placed");
        orderRequest.setComplete(true);
        return orderRequest;
    }
}
