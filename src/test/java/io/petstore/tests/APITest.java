package io.petstore.tests;

import com.github.javafaker.Faker;
import io.petstore.api.PetStoreApiClient;
import org.junit.jupiter.api.BeforeEach;

public abstract class APITest {

    protected PetStoreApiClient apiClient;
    protected Faker faker;

    @BeforeEach
    void setUp() {
        apiClient = new PetStoreApiClient();
        faker = new Faker();

    }
}
