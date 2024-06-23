package pl.edu.wszib.api.order;

import pl.edu.wszib.api.product.ProductApi;

import java.math.BigDecimal;

public record OrderLineApi(
        String id,
        ProductApi product,
        Integer quantity,
        BigDecimal amount) {
    public OrderLineApi addQuantity(final Integer quantity) {
        final Integer changedQuantity = this.quantity + quantity;
        final BigDecimal changedAmount = product.price().multiply(BigDecimal.valueOf(changedQuantity));
        return new OrderLineApi(this.id, product, changedQuantity, changedAmount);
    }
}
