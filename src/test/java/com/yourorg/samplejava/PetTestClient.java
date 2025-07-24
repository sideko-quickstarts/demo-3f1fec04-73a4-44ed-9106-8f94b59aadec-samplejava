
package com.yourorg.samplejava;

import java.util.List;

import com.yourorg.samplejava.core.ApiException;
import com.yourorg.samplejava.model.Category;
import com.yourorg.samplejava.model.PetFindByStatusStatusEnum;
import com.yourorg.samplejava.model.PetStatusEnum;
import com.yourorg.samplejava.model.Tag;
import com.yourorg.samplejava.resources.pet.params.CreateRequest;
import com.yourorg.samplejava.resources.pet.params.DeleteRequest;
import com.yourorg.samplejava.resources.pet.params.FindByStatusRequest;
import com.yourorg.samplejava.resources.pet.params.GetRequest;
import com.yourorg.samplejava.resources.pet.params.UpdateRequest;
import com.yourorg.samplejava.resources.pet.params.UploadImageRequest;
import org.junit.jupiter.api.Test;


public final class PetTestClient {
    @Test
    void testDelete200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().delete(DeleteRequest
                                .builder()
                                .petId(123)
                                .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testFindByStatus200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().findByStatus(FindByStatusRequest
                                      .builder()
                                      .status(PetFindByStatusStatusEnum.AVAILABLE)
                                      .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testFindByStatus200SuccessRequiredOnly() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().findByStatus(FindByStatusRequest
                                      .builder()
                                      .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testGet200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().get(GetRequest
                             .builder()
                             .petId(123)
                             .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testCreate200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().create(CreateRequest
                                .builder()
                                .category(Category
                                          .builder()
                                          .id(1)
                                          .name("Dogs")
                                          .build())
                                .id(10)
                                .name("doggie")
                                .photoUrls(List.of(
                                               "string"
                                           ))
                                .status(PetStatusEnum.AVAILABLE)
                                .tags(List.of(
                                          Tag
                                          .builder()
                                          .id(123)
                                          .name("string")
                                          .build()
                                      ))
                                .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testUploadImage200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().uploadImage(UploadImageRequest
                                     .builder()
            .data(((java.util.function.Function<Void, java.io.File>)(v -> { try { return java.nio.file.Files.write(java.nio.file.Files.createTempFile("prefix", ".tmp"), "content".getBytes()).toFile(); } catch (Exception e) { return new java.io.File("fallback.tmp"); }})).apply(
                null))
            .petId(123)
            .additionalMetadata("string")
            .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testUploadImage200SuccessRequiredOnly() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().uploadImage(UploadImageRequest
                                     .builder()
            .data(((java.util.function.Function<Void, java.io.File>)(v -> { try { return java.nio.file.Files.write(java.nio.file.Files.createTempFile("prefix", ".tmp"), "content".getBytes()).toFile(); } catch (Exception e) { return new java.io.File("fallback.tmp"); }})).apply(
                null))
            .petId(123)
            .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
    @Test
    void testUpdate200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.pet().update(UpdateRequest
                                .builder()
                                .category(Category
                                          .builder()
                                          .id(1)
                                          .name("Dogs")
                                          .build())
                                .id(10)
                                .name("doggie")
                                .photoUrls(List.of(
                                               "string"
                                           ))
                                .status(PetStatusEnum.AVAILABLE)
                                .tags(List.of(
                                          Tag
                                          .builder()
                                          .id(123)
                                          .name("string")
                                          .build()
                                      ))
                                .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
}
