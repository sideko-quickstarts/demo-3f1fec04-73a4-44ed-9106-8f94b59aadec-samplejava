
package com.yourorg.samplejava;

import java.util.function.Supplier;

import com.yourorg.samplejava.core.BaseClient;
import com.yourorg.samplejava.core.Suppliers;
import com.yourorg.samplejava.resources.pet.PetClient;
import com.yourorg.samplejava.resources.store.StoreClient;


public class Client {

    protected final BaseClient baseClient;
    protected final Supplier<PetClient> pet;
    protected final Supplier<StoreClient> store;

    public Client(BaseClient baseClient) {
        this.baseClient = baseClient;
        this.pet = Suppliers.memoize(() -> new PetClient(baseClient));
        this.store = Suppliers.memoize(() -> new StoreClient(baseClient));
    }

    public PetClient pet() {
        return this.pet.get();
    }

    public StoreClient store() {
        return this.store.get();
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

}
