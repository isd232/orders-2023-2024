package pl.edu.wszib.infrastructure.product;

import pl.edu.wszib.api.product.ProductApi;
import pl.edu.wszib.application.product.ProductRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SpringDataJpaProductRepository implements ProductRepository {
    private final SpringDataJpaProductDao productDao;

    public SpringDataJpaProductRepository(final SpringDataJpaProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductApi save(ProductApi productApi) {
        productDao.save(new ProductEntity(
                productApi.id(),
                productApi.name(),
                productApi.price(),
                productApi.createdAt(),
                productApi.updatedAt()
        ));
        return productApi;
    }

    @Override
    public Optional<ProductApi> findById(String id) {
        return productDao.findById(id)
                .map(SpringDataJpaProductRepository::toApi);
    }

    @Override
    public boolean existsById(String id) {
        return productDao.existsById(id);
    }

    @Override
    public Collection<ProductApi> findAll() {
        return productDao.findAll().stream()
                .map(SpringDataJpaProductRepository::toApi)
                .toList();
    }

    @Override
    public Set<ProductApi> findByIds(Set<String> productIds) {
        return productDao.findAllById(productIds)
                .stream()
                .map(SpringDataJpaProductRepository::toApi)
                .collect(Collectors.toSet());
    }

    private static ProductApi toApi(ProductEntity product) {
        return new ProductApi(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
