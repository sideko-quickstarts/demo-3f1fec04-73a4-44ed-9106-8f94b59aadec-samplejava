
### Delete purchase order by identifier. <a name="delete"></a>

For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors.

**API Endpoint**: `DELETE /store/order/{orderId}`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `orderId` | ✓ | ID of the order that needs to be deleted | `123` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.store.order.params.DeleteRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.store().order().delete(DeleteRequest
                       .builder()
                       .orderId(123)
                       .build());
```

### Find purchase order by ID. <a name="get"></a>

For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions.

**API Endpoint**: `GET /store/order/{orderId}`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `orderId` | ✓ | ID of order that needs to be fetched | `123` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.store.order.params.GetRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.store().order().get(GetRequest
                       .builder()
                       .orderId(123)
                       .build());
```

### Place an order for a pet. <a name="create"></a>

Place a new order in the store.

**API Endpoint**: `POST /store/order`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `complete` | ✗ |  | `true` |
| `id` | ✗ |  | `10` |
| `petId` | ✗ |  | `198772` |
| `quantity` | ✗ |  | `7` |
| `shipDate` | ✗ |  | `"1970-01-01T00:00:00"` |
| `status` | ✗ | Order Status | `OrderStatusEnum.APPROVED` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.model.Order;
import com.yourorg.samplejava.model.OrderStatusEnum;
import com.yourorg.samplejava.resources.store.order.params.CreateRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
Order res = client.store().order().create(CreateRequest
            .builder()
            .id(10)
            .petId(198772)
            .quantity(7)
            .status(OrderStatusEnum.APPROVED)
            .build());
```

#### Response

##### Type
[Order](/src/main/java/com/yourorg/samplejava/model/Order.java)

##### Example
`Order
.builder()
.id(10)
.petId(198772)
.quantity(7)
.status(OrderStatusEnum.APPROVED)
.build()`
