
package com.yourorg.samplejava;

import com.yourorg.samplejava.core.ApiException;
import com.yourorg.samplejava.model.OrderStatusEnum;
import com.yourorg.samplejava.resources.store.order.params.CreateRequest;
import com.yourorg.samplejava.resources.store.order.params.DeleteRequest;
import com.yourorg.samplejava.resources.store.order.params.GetRequest;
import org.junit.jupiter.api.Test;


public final class StoreOrderTestClient {
    @Test
    void testDelete200SuccessAllParams() {
        Client client = Client
                        .builder()
                        .environment(Environment.MOCK_SERVER)
                        .withApiKey("API_KEY")
                        .build();

        try {
            client.store().order().delete(DeleteRequest
                                          .builder()
                                          .orderId(123)
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
            client.store().order().get(GetRequest
                                       .builder()
                                       .orderId(123)
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
            client.store().order().create(CreateRequest
                                          .builder()
                                          .complete(true)
                                          .id(10)
                                          .petId(198772)
                                          .quantity(7)
                                          .shipDate("1970-01-01T00:00:00")
                                          .status(OrderStatusEnum.APPROVED)
                                          .build());
        } catch (ApiException e) {
            System.err.println("Error msg: " + e.toString());
            throw e;
        }
    }
}
