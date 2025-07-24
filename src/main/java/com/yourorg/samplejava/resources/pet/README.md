
### Deletes a pet. <a name="delete"></a>

Delete a pet.

**API Endpoint**: `DELETE /pet/{petId}`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `petId` | ✓ | Pet id to delete | `123` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.pet.params.DeleteRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.pet().delete(DeleteRequest
                       .builder()
                       .petId(123)
                       .build());
```

### Finds Pets by status. <a name="find_by_status"></a>

Multiple status values can be provided with comma separated strings.

**API Endpoint**: `GET /pet/findByStatus`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `status` | ✗ | Status values that need to be considered for filter | `PetFindByStatusStatusEnum.AVAILABLE` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.pet.params.FindByStatusRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.pet().findByStatus(FindByStatusRequest
                       .builder()
                       .build());
```

### Find pet by ID. <a name="get"></a>

Returns a single pet.

**API Endpoint**: `GET /pet/{petId}`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `petId` | ✓ | ID of pet to return | `123` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.pet.params.GetRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.pet().get(GetRequest
                                        .builder()
                                        .petId(123)
                                        .build());
```

### Add a new pet to the store. <a name="create"></a>

Add a new pet to the store.

**API Endpoint**: `POST /pet`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `name` | ✓ |  | `"doggie"` |
| `photoUrls` | ✓ |  | `List.of("string")` |
| `category` | ✗ |  | `Category.builder().id(1).name("Dogs").build()` |
| `id` | ✗ |  | `10` |
| `status` | ✗ | pet status in the store | `PetStatusEnum.AVAILABLE` |
| `tags` | ✗ |  | `List.of(Tag.builder().build())` |

#### Example Snippet

```java
import java.util.List;

import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.pet.params.CreateRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.pet().create(CreateRequest
                       .builder()
                       .id(10)
                       .name("doggie")
                       .photoUrls(List.of(
                                      "string"
                                  ))
                       .build());
```

### Uploads an image. <a name="upload_image"></a>

Upload image of the pet.

**API Endpoint**: `POST /pet/{petId}/uploadImage`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `data` | ✓ |  | `new java.io.File("uploads/file.pdf")` |
| `petId` | ✓ | ID of pet to update | `123` |
| `additionalMetadata` | ✗ | Additional Metadata | `"string"` |

#### Example Snippet

```java
import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.model.ApiResponse;
import com.yourorg.samplejava.resources.pet.params.UploadImageRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
ApiResponse res = client.pet().uploadImage(UploadImageRequest
                  .builder()
                  .data(new java.io.File("uploads/file.pdf"))
                  .petId(123)
                  .build());
```

#### Response

##### Type
[ApiResponse](/src/main/java/com/yourorg/samplejava/model/ApiResponse.java)

##### Example
`ApiResponse
.builder()
.build()`

### Update an existing pet. <a name="update"></a>

Update an existing pet by Id.

**API Endpoint**: `PUT /pet`

#### Parameters

| Parameter | Required | Description | Example |
|-----------|:--------:|-------------|--------|
| `name` | ✓ |  | `"doggie"` |
| `photoUrls` | ✓ |  | `List.of("string")` |
| `category` | ✗ |  | `Category.builder().id(1).name("Dogs").build()` |
| `id` | ✗ |  | `10` |
| `status` | ✗ | pet status in the store | `PetStatusEnum.AVAILABLE` |
| `tags` | ✗ |  | `List.of(Tag.builder().build())` |

#### Example Snippet

```java
import java.util.List;

import com.yourorg.samplejava.Client;
import com.yourorg.samplejava.resources.pet.params.UpdateRequest;

Client client = Client
                .builder()
                .withApiKey(System.getenv("API_KEY"))
                .build();
okhttp3.Response res = client.pet().update(UpdateRequest
                       .builder()
                       .id(10)
                       .name("doggie")
                       .photoUrls(List.of(
                                      "string"
                                  ))
                       .build());
```
