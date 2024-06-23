package pl.edu.wszib.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaOrderDao extends JpaRepository<OrderEntity, String> {
}
