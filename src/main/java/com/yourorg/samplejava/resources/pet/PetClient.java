
package com.yourorg.samplejava.resources.pet;

import java.io.IOException;

import com.yourorg.samplejava.core.ApiException;
import com.yourorg.samplejava.core.BaseClient;
import com.yourorg.samplejava.core.HttpRequestBuilder;
import com.yourorg.samplejava.core.RequestOptions;
import com.yourorg.samplejava.core.ResponseHandler;
import com.yourorg.samplejava.model.ApiResponse;
import com.yourorg.samplejava.model.Pet;
import com.yourorg.samplejava.resources.pet.params.CreateRequest;
import com.yourorg.samplejava.resources.pet.params.DeleteRequest;
import com.yourorg.samplejava.resources.pet.params.FindByStatusRequest;
import com.yourorg.samplejava.resources.pet.params.GetRequest;
import com.yourorg.samplejava.resources.pet.params.UpdateRequest;
import com.yourorg.samplejava.resources.pet.params.UploadImageRequest;


public class PetClient {
    protected final BaseClient baseClient;

    public PetClient(BaseClient baseClient) {
        this.baseClient = baseClient;

    }

    /**
    * Deletes a pet.
    *
    * Delete a pet.
    *
    * DELETE /pet/{petId}
    */
    public okhttp3.Response delete (DeleteRequest request) {
        return delete (request, null);
    }


    /**
    * Deletes a pet.
    *
    * Delete a pet.
    *
    * DELETE /pet/{petId}
    */
    public okhttp3.Response delete (DeleteRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet/{petId}")
            .addPathParam("petId", request.getPetId())
            .method("DELETE")
            .addAuth(baseClient.getAuth("api_key"))
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Finds Pets by status.
    *
    * Multiple status values can be provided with comma separated strings.
    *
    * GET /pet/findByStatus
    */
    public okhttp3.Response findByStatus() {
        return findByStatus(FindByStatusRequest.builder().build(), null);
    }


    /**
    * Finds Pets by status.
    *
    * Multiple status values can be provided with comma separated strings.
    *
    * GET /pet/findByStatus
    */
    public okhttp3.Response findByStatus(FindByStatusRequest request) {
        return findByStatus(request, null);
    }


    /**
    * Finds Pets by status.
    *
    * Multiple status values can be provided with comma separated strings.
    *
    * GET /pet/findByStatus
    */
    public okhttp3.Response findByStatus(FindByStatusRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet/findByStatus")
            .addQueryParam("status", request.getStatus(), "form", true)
            .method("GET")
            .addAuth(baseClient.getAuth("api_key"))
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Find pet by ID.
    *
    * Returns a single pet.
    *
    * GET /pet/{petId}
    */
    public okhttp3.Response get(GetRequest request) {
        return get(request, null);
    }


    /**
    * Find pet by ID.
    *
    * Returns a single pet.
    *
    * GET /pet/{petId}
    */
    public okhttp3.Response get(GetRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet/{petId}")
            .addPathParam("petId", request.getPetId())
            .method("GET")
            .addAuth(baseClient.getAuth("api_key"))
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Add a new pet to the store.
    *
    * Add a new pet to the store.
    *
    * POST /pet
    */
    public okhttp3.Response create(CreateRequest request) {
        return create(request, null);
    }


    /**
    * Add a new pet to the store.
    *
    * Add a new pet to the store.
    *
    * POST /pet
    */
    public okhttp3.Response create(CreateRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet")
            .method("POST")
            .addAuth(baseClient.getAuth("api_key"))
            .setJsonBody(Pet.builder().category(request.getCategory()).id(request.getId()).status(
                             request.getStatus()).tags(request.getTags()).name(request.getName()).photoUrls(
                             request.getPhotoUrls()).build())
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Uploads an image.
    *
    * Upload image of the pet.
    *
    * POST /pet/{petId}/uploadImage
    */
    public ApiResponse uploadImage(UploadImageRequest request) {
        return uploadImage(request, null);
    }


    /**
    * Uploads an image.
    *
    * Upload image of the pet.
    *
    * POST /pet/{petId}/uploadImage
    */
    public ApiResponse uploadImage(UploadImageRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet/{petId}/uploadImage")
            .addPathParam("petId", request.getPetId())
            .addQueryParam("additionalMetadata", request.getAdditionalMetadata(), "form", true)
            .method("POST")
            .addAuth(baseClient.getAuth("api_key"))
            .setBinaryBody(request.getData(), "application/octet-stream")
            .execute();
            return ResponseHandler.processJsonResponse(response, ApiResponse.class);
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Update an existing pet.
    *
    * Update an existing pet by Id.
    *
    * PUT /pet
    */
    public okhttp3.Response update(UpdateRequest request) {
        return update(request, null);
    }


    /**
    * Update an existing pet.
    *
    * Update an existing pet by Id.
    *
    * PUT /pet
    */
    public okhttp3.Response update(UpdateRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/pet")
            .method("PUT")
            .addAuth(baseClient.getAuth("api_key"))
            .setJsonBody(Pet.builder().category(request.getCategory()).id(request.getId()).status(
                             request.getStatus()).tags(request.getTags()).name(request.getName()).photoUrls(
                             request.getPhotoUrls()).build())
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }







}
