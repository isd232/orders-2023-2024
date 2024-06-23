package pl.edu.wszib.infrastructure.order;

import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.application.order.OrderRepository;
import pl.edu.wszib.application.product.ProductRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpringDataJpaOrderRepository implements OrderRepository {
    private final SpringDataJpaOrderDao orderDao;
    private final ProductRepository productRepository;

    public SpringDataJpaOrderRepository(SpringDataJpaOrderDao orderDao,
                                        ProductRepository productRepository) {
        this.orderDao = orderDao;
        this.productRepository = productRepository;
    }

    @Override
    public OrderApi save(OrderApi order) {
        Set<OrderLineEntity> lineEntities = order.lines()
                .stream()
                .map(line -> new OrderLineEntity(line.id(), line.product().id(), line.quantity(), line.amount(), null))
                .collect(Collectors.toSet());
        OrderEntity orderEntity = new OrderEntity(order.id(), order.createdAt(), order.updatedAt(), order.amount(), lineEntities);
        lineEntities.forEach(line -> line.setOrder(orderEntity));
        orderDao.save(orderEntity);
        return order;
    }

    @Override
    public Optional<OrderApi> findById(String orderId) {
        return orderDao.findById(orderId)
                .map(this::toApi);
    }

    private OrderApi toApi(OrderEntity order) {
        final Set<String> productIds = order.getLines()
                .stream()
                .map(OrderLineEntity::getProductId)
                .collect(Collectors.toSet());
        final Set<ProductApi> products = productRepository.findByIds(productIds);
        final Map<String, ProductApi> productsById = products.stream()
                .collect(Collectors.toMap(ProductApi::id, Function.identity()));
        return new OrderApi(
                order.getId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getAmount(),
                toApi(order.getLines(), productsById)
        );
    }

    private Set<OrderLineApi> toApi(Set<OrderLineEntity> lines, Map<String, ProductApi> products) {
        return lines.stream()
                .map(line -> toApi(line, products))
                .collect(Collectors.toSet());
    }

    private OrderLineApi toApi(OrderLineEntity line, Map<String, ProductApi> products) {
        return new OrderLineApi(
                line.getId(),
                products.get(line.getProductId()),
                line.getQuantity(),
                line.getAmount()
        );
    }
    // TODO TASK 1 zaimplementuj repo dla zamówień analogicznie jak w przypadku produktów
    //  w pierwszej kolejności zapisuj tylko dane z "nagłówka" zamówienia (samo OrderEntity, bez pozycji)
}
