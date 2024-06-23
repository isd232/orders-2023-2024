package pl.edu.wszib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

//        System.out.println("Hello world!");
//
//        String productId = UUID.randomUUID().toString();
//
//        ProductApi productApi = new ProductApi(
//                productId,
//                "Testowy produkt 1",
//                new BigDecimal("10.25"),
//                Instant.now(),
//                Instant.now()
//        );
//
//        ProductApi product2Api = new ProductApi(
//                productId,
//                "Testowy produkt 1",
//                new BigDecimal("10.25"),
//                Instant.now(),
//                Instant.now()
//        );
//        if (Objects.equals(productApi, product2Api)) {
//            System.out.println("Produkty są takie same");
//        } else {
//            System.out.println("Produkty nie są takie same");
//        }
//
//        System.out.println("Dane produktu 1: " + productApi);
//        System.out.println("Dane produktu 2: " + product2Api);
//
//        Set<OrderLineApi> lines = new LinkedHashSet<>();
//        OrderLineApi line1 = new OrderLineApi(productApi, 1, productApi.price());
//        lines.add(line1);
//        OrderApi order = new OrderApi(
//                UUID.randomUUID().toString(),
//                Instant.now(),
//                Instant.now(),
//                productApi.price(),
//                lines
//        );
//        System.out.println("Dane zamówienia: " + order);
////        order.lines().add(new OrderLineApi(product2Api, 2, product2Api.price().multiply(BigDecimal.valueOf(2))));
////        System.out.println("Dane zamówienia: " + order);
//
//        ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());
//        OrderFacadeApi orderFacade = new OrderFacade(new UUIDOrderIdProvider(), new InMemoryOrderRepository(), productFacade, Clock.systemDefaultZone());
//        OrderApi order1 = orderFacade.create();
//        OrderApi order2 = orderFacade.create(line1);
    }
}