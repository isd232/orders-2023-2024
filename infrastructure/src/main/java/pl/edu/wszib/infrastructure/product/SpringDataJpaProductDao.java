package pl.edu.wszib.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaProductDao extends JpaRepository<ProductEntity, String> {

}
