package br.com.spark.monolito.domain.model;

import br.com.spark.monolito.domain.model.enuns.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @NotNull
    @Column(name = "total_price", precision = 10, scale = 5, nullable = false)
    @ToString.Include
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @ToString.Include
    private OrderStatus status;

    @Column(name = "shipped")
    @ToString.Include
    private ZonedDateTime shipped;

    @OneToOne(mappedBy = "order")
    @ToString.Include
    private Payment payment;

    @Embedded
    @ToString.Include
    private Address shipmentAddress;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderItem> orderItems;

    public void updateTotal(BigDecimal price, Long quantity) {
        this.totalPrice = price.multiply(BigDecimal.valueOf(quantity));
    }
}
