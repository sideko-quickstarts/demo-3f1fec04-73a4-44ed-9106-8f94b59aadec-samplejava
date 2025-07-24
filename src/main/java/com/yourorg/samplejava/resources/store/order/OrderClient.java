
package com.yourorg.samplejava.resources.store.order;

import java.io.IOException;
import java.util.Map;

import com.yourorg.samplejava.core.ApiException;
import com.yourorg.samplejava.core.BaseClient;
import com.yourorg.samplejava.core.HttpRequestBuilder;
import com.yourorg.samplejava.core.RequestOptions;
import com.yourorg.samplejava.core.ResponseHandler;
import com.yourorg.samplejava.model.Order;
import com.yourorg.samplejava.resources.store.order.params.CreateRequest;
import com.yourorg.samplejava.resources.store.order.params.DeleteRequest;
import com.yourorg.samplejava.resources.store.order.params.GetRequest;


public class OrderClient {
    protected final BaseClient baseClient;

    public OrderClient(BaseClient baseClient) {
        this.baseClient = baseClient;

    }

    /**
    * Delete purchase order by identifier.
    *
    * For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors.
    *
    * DELETE /store/order/{orderId}
    */
    public okhttp3.Response delete (DeleteRequest request) {
        return delete (request, null);
    }


    /**
    * Delete purchase order by identifier.
    *
    * For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors.
    *
    * DELETE /store/order/{orderId}
    */
    public okhttp3.Response delete (DeleteRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/store/order/{orderId}")
            .addPathParam("orderId", request.getOrderId())
            .method("DELETE")
            .addAuth(baseClient.getAuth("api_key"))
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Find purchase order by ID.
    *
    * For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions.
    *
    * GET /store/order/{orderId}
    */
    public okhttp3.Response get(GetRequest request) {
        return get(request, null);
    }


    /**
    * Find purchase order by ID.
    *
    * For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions.
    *
    * GET /store/order/{orderId}
    */
    public okhttp3.Response get(GetRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/store/order/{orderId}")
            .addPathParam("orderId", request.getOrderId())
            .method("GET")
            .addAuth(baseClient.getAuth("api_key"))
            .execute();
            return response;
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }

    /**
    * Place an order for a pet.
    *
    * Place a new order in the store.
    *
    * POST /store/order
    */
    public Order create(CreateRequest request) {
        return create(request, null);
    }


    /**
    * Place an order for a pet.
    *
    * Place a new order in the store.
    *
    * POST /store/order
    */
    public Order create(CreateRequest request, RequestOptions requestOptions) {

        try {
            okhttp3.Response response = new HttpRequestBuilder()
            .baseUrl(this.baseClient.baseUrl())
            .path("/store/order")
            .method("POST")
            .addAuth(baseClient.getAuth("api_key"))
            .setFormUrlEncodedBody(Order.builder().complete(request.getComplete()).id(request.getId()).petId(
                                       request.getPetId()).quantity(request.getQuantity()).shipDate(request.getShipDate()).status(
                                       request.getStatus()).build(), Map.of("complete", "form", "id", "form", "petId", "form", "quantity",
                                               "form", "shipDate", "form", "status", "form"), Map.of("complete", true, "id", true, "petId", true,
                                                       "quantity", true, "shipDate", true, "status", true))
            .execute();
            return ResponseHandler.processJsonResponse(response, Order.class);
        } catch (IOException e) {
            throw new ApiException("Error executing HTTP request", e);
        }
    }




}
