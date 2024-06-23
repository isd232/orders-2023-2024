package pl.edu.wszib.infrastructure.order;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
public class OrderEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "order")
    private Set<OrderLineEntity> lines;

    protected OrderEntity() {
        // for hibernate
    }

    public OrderEntity(String id,
                       Instant createdAt,
                       Instant updatedAt,
                       BigDecimal amount,
                       Set<OrderLineEntity> lines) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.amount = amount;
        this.lines = lines;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Set<OrderLineEntity> getLines() {
        return lines;
    }
}
