package pl.edu.wszib.client.order;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderFacadeApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.api.order.OrderResult;

import java.util.Set;

public class OrderRestClient implements OrderFacadeApi {
    private final RestClient restClient;

    private OrderRestClient(final RestClient restClient) {
        this.restClient = restClient;
    }

    public static OrderRestClient createClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .messageConverters(consumer -> consumer.addFirst(new MappingJackson2HttpMessageConverter()))
                .build();
//        final RestClient restClient = RestClient.create();
        return new OrderRestClient(restClient);
    }

    @Override
    public OrderApi create(Set<OrderLineApi> lines) {
        ResponseEntity<OrderApi> orderResponse = restClient.post()
                .uri("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(OrderApi.class);
        if (orderResponse.getStatusCode().is2xxSuccessful()) {
            return orderResponse.getBody();
        }
        throw new IllegalStateException("Incorrect response code: " + orderResponse.getStatusCode());
    }

    @Override
    public OrderResult getById(String orderId) {
        // TODO Task 1: implementacja
        return null;
    }

    @Override
    public OrderResult addProduct(String orderId, String productId, Integer quantity) {
        return null;
    }

    @Override
    public OrderResult removeProduct(String orderId, String productId) {
        return null;
    }

    @Override
    public OrderResult changeQuantity(String orderId, String productId, Integer quantity) {
        return null;
    }

    @Override
    public OrderResult increaseQuantity(String orderId, String productId) {
        return null;
    }

    @Override
    public OrderResult decreaseQuantity(String orderId, String productId) {
        return null;
    }
}
