
package com.yourorg.samplejava.resources.store;

import java.util.function.Supplier;

import com.yourorg.samplejava.core.BaseClient;
import com.yourorg.samplejava.core.Suppliers;
import com.yourorg.samplejava.resources.store.order.OrderClient;


public class StoreClient {
    protected final BaseClient baseClient;
    protected final Supplier<OrderClient> order;
    public StoreClient(BaseClient baseClient) {
        this.baseClient = baseClient;
        this.order = Suppliers.memoize(() -> new OrderClient(baseClient));
    }

    public OrderClient order() {
        return this.order.get();
    }
}
