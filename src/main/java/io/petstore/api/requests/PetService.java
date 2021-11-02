package io.petstore.api.requests;

import io.petstore.api.Request;
import io.petstore.api.model.pet.PetRequest;

public class PetService {

    private static final String ENDPOINT = "/pet";

    public static Request postCreatePet(final PetRequest petRequest) {
        return new Request(
                "[POST] запрос создания нового PET",
                given -> given.body(petRequest).post(ENDPOINT)
        );
    }

    public static Request getPetById(final int id) {
        return new Request(
                "[GET] запрос получения PET по id",
                given -> given.get(ENDPOINT + "/" + id)
        );
    }

    public static Request getPetByStatus(final String status) {
        return new Request(
                "[GET] запрос получения PET по status",
                given -> given.get(ENDPOINT + "/findByStatus?status=" + status)
        );
    }

    public static Request getPetByListStatus(final String... statuses) {
        final StringBuilder sb = new StringBuilder();
        for (String status : statuses) {
            sb.append("status=").append(status).append("&");
        }
        sb.replace(sb.length() - 1, sb.length(), "");

        return new Request(
                "[GET] запрос получения PET по status",
                given -> given.get(ENDPOINT + "/findByStatus?" + sb)
        );
    }

    public static Request putUpdatePet(final PetRequest petRequest) {
        return new Request(
                "[PUT] запрос обновления существующего PET",
                given -> given.body(petRequest).put(ENDPOINT)
        );
    }

    public static Request deletePetById(final String id) {
        return new Request(
                "[DELETE] запрос удаления PET по id",
                given -> given.delete(ENDPOINT + "/" + id)
        );
    }
}
