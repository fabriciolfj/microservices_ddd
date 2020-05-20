package br.com.spark.service.order.domain.model;

import br.com.spark.service.order.domain.model.enuns.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", precision = 10, scale = 5, nullable = false)
    @ToString.Include
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @ToString.Include
    private OrderStatus status;

    @Column(name = "shipped")
    @ToString.Include
    private ZonedDateTime shipped;

    @Embedded
    @ToString.Include
    private Address shipmentAddress;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public void updateTotal(BigDecimal price) {
        this.totalPrice = this.totalPrice.add(price);
    }
}
