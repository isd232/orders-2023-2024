package pl.edu.wszib.infrastructure.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.api.order.OrderAddLineApi;
import pl.edu.wszib.api.order.OrderApi;
import pl.edu.wszib.api.order.OrderLineApi;
import pl.edu.wszib.api.order.OrderResult;
import pl.edu.wszib.application.order.OrderFacade;

@RestController
@RequestMapping("/orders")
public class OrderEndpoint {
    private final OrderFacade orderFacade;

    public OrderEndpoint(final OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping
    public OrderApi createOrder() {
        return orderFacade.create();
    }

    @PostMapping("/{orderId}/lines")
    public ResponseEntity<?> createOrderLine(@PathVariable String orderId,
                                             @RequestBody OrderAddLineApi orderAddLine) {
        OrderResult result = orderFacade.addProduct(orderId, orderAddLine.productId(), orderAddLine.quantity());
        return toResponse(result);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable final String orderId) {
        OrderResult result = orderFacade.getById(orderId);
        return toResponse(result);
    }

    private static ResponseEntity<?> toResponse(OrderResult result) {
        return switch (result) {
            case OrderResult.SuccessOrderResult success -> ResponseEntity.ok(success);
            case OrderResult.FailureOrderResult failure -> new ResponseEntity<>(failure, HttpStatus.NOT_FOUND);
        };
    }
}
