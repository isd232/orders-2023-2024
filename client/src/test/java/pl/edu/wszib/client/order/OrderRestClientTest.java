package pl.edu.wszib.client.order;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.api.order.OrderApi;

class OrderRestClientTest {

    private final OrderRestClient orderRestClient = OrderRestClient.createClient();

    @Test
    public void exampleCreateOrder() {
        final OrderApi orderApi = orderRestClient.create();

        System.out.println(orderApi);
    }
}