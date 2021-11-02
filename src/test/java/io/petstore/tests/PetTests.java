package io.petstore.tests;

import io.petstore.api.PetStoreApiClient;
import io.petstore.api.model.pet.PetRequest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.petstore.api.requests.PetService.*;
import static org.hamcrest.Matchers.equalTo;

@Feature("pet")
@DisplayName("Everything about your Pets")
public class PetTests extends APITest {

    @DisplayName("Создание нового питомца")
    @Tag("Smoke")
    @Test
    public void test00() {
        final PetRequest petRequest = makePetRequest();
        Response response = apiClient.send(postCreatePet(petRequest));
        response.then().statusCode(200);
        response = apiClient.send(getPetById(petRequest.getId()));
        response.then().statusCode(200);
    }

    @DisplayName("Получение существующего питомца по id")
    @Test
    public void test01() {
        apiClient.send(getPetById(-10)).then().statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @DisplayName("Получение всех питомцев по статусу")
    @ParameterizedTest
    @ValueSource(strings = {"pending", "available", "sold"})
    public void test02(final String status) {
        apiClient.send(getPetByStatus(status)).then().statusCode(200);
    }

    @DisplayName("Получение питомцев с пустым статусом")
    @Test
    public void test03() {
        apiClient.send(getPetByStatus("")).then().statusCode(200);
    }

    @DisplayName("Получение питомцев по 3-м статусам")
    @Test
    public void testGetPetByListStatus() {
        apiClient.send(getPetByListStatus("available", "pending", "sold")).then().statusCode(200);
    }

    @DisplayName("Обновление существующего питомца")
    @Test
    public void test04() {
        final PetRequest petRequest = makePetRequest();
        Response response = apiClient.send(postCreatePet(petRequest));
        response.then().statusCode(200);
        final String updateStatus = "pending";
        petRequest.setStatus(updateStatus);
        response = apiClient.send(putUpdatePet(petRequest));
        response.then().statusCode(200).body("status", equalTo(updateStatus));
    }

    @DisplayName("Удаление существующего питомца по id")
    @Test
    public void test05() {
        final PetRequest petRequest = makePetRequest();
        Response response = apiClient.send(postCreatePet(petRequest));
        response.then().statusCode(200);
        final String id = String.valueOf(petRequest.getId());
        response = apiClient.send(deletePetById(id));
        response.then().statusCode(200);
        response = apiClient.send(deletePetById(id));
        response.then().statusCode(404);
    }

    @DisplayName("Удаление питомца по пустому id")
    @Test
    public void test06() {
        new PetStoreApiClient("9999").send(deletePetById("")).then().statusCode(404);
    }

    private PetRequest makePetRequest() {
        final PetRequest petRequest = new PetRequest();
        petRequest.setId(faker.number().numberBetween(199, 9999));
        petRequest.setName(faker.name().username());
        List<String> statusList = Arrays.asList("available", "pending", "sold");
        Collections.shuffle(statusList);
        petRequest.setStatus(statusList.get(0));
        return petRequest;
    }
}
