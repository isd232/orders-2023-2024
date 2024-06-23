package pl.edu.wszib.api.product;

import java.util.Collection;

public interface ProductFacadeApi {
    ProductResult create(ProductApi productApi);
    ProductResult getById(String id);

    Collection<ProductApi> getAll();
}
