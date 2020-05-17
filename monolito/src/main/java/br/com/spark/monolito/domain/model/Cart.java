package br.com.spark.monolito.domain.model;

import br.com.spark.monolito.domain.model.enuns.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cart")
public class Cart extends AbstractEntity {

    private static final long serialVersionUID = 6409390179928246365L;

    @OneToOne
    @JoinColumn(unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    public Cart(Customer customer) {
        this.customer= customer;
        this.status = CartStatus.NEW;
    }
}
