package pl.edu.wszib.application.product;

import pl.edu.wszib.api.product.ProductApi;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {
    ProductApi save(ProductApi productApi);

    Optional<ProductApi> findById(String id);

    boolean existsById(String id);

    Collection<ProductApi> findAll();

    Set<ProductApi> findByIds(Set<String> productIds);
}
