package pl.edu.wszib.application.order;

import pl.edu.wszib.api.order.OrderApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, OrderApi> orders = new HashMap<>();

    @Override
    public OrderApi save(OrderApi order) {
        orders.put(order.id(), order);
        return order;
    }

    @Override
    public Optional<OrderApi> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
