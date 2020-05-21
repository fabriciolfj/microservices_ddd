package br.com.spark.service.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordercart")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", precision = 10, scale = 5, nullable = false)
    @ToString.Include
    private BigDecimal totalPrice;

    @Column(name = "shipped")
    @ToString.Include
    private LocalDate shipped;

    @Embedded
    @ToString.Include
    private Address shipmentAddress;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.DETACH } )
    private Set<OrderItem> orderItems = new HashSet<>();

    private void updateTotal() {
        this.totalPrice =
                this.orderItems.stream().map(o -> o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())))
                .reduce(BigDecimal.ZERO, (total, element) -> total.add(element));
    }

    public void addItens(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        updateTotal();
    }
}
