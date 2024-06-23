package pl.edu.wszib.infrastructure.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.api.product.ProductFacadeApi;
import pl.edu.wszib.application.order.OrderFacade;
import pl.edu.wszib.application.order.OrderIdProvider;
import pl.edu.wszib.application.order.OrderRepository;
import pl.edu.wszib.application.order.UUIDOrderIdProvider;
import pl.edu.wszib.application.product.ProductRepository;

import java.time.Clock;

@Configuration
public class OrderConfiguration {
    @Bean
    OrderFacade orderFacade(OrderIdProvider orderIdProvider,
                            OrderRepository orderRepository,
                            ProductFacadeApi productFacade) {
        return new OrderFacade(orderIdProvider, orderRepository, productFacade, Clock.systemDefaultZone());
    }

    @Bean
    OrderIdProvider orderIdProvider() {
        return new UUIDOrderIdProvider();
    }

//    @Bean
//    OrderRepository orderRepository() {
//        return new InMemoryOrderRepository();
//    }

    @Bean
    OrderRepository orderRepository(SpringDataJpaOrderDao orderDao,
                                    ProductRepository productRepository) {
        return new SpringDataJpaOrderRepository(orderDao, productRepository);
    }
}
