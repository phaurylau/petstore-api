package io.petstore.api.requests;

import io.petstore.api.Request;
import io.petstore.api.model.store.OrderRequest;

public class StoreService {

    private static final String ENDPOINT = "/store";

    public static Request postCreateOrder(final OrderRequest orderRequest) {
        return new Request(
                "",
                given -> given.body(orderRequest).post(ENDPOINT + "/order")
        );
    }

    public static Request getOrderById(final int id) {
        return new Request(
                "",
                given -> given.get(ENDPOINT + "/order/" + id)
        );
    }

    public static Request deleteOrderById(final int id) {
        return new Request(
                "",
                given -> given.delete(ENDPOINT + "/order/" + id)
        );
    }

    public static Request getInventory() {
        return new Request(
                "",
                given -> given.get(ENDPOINT + "/inventory/")
        );
    }
}
