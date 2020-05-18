package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.CartDto;
import br.com.spark.monolito.domain.model.Cart;
import br.com.spark.monolito.domain.model.enuns.CartStatus;
import br.com.spark.monolito.domain.repository.CartRepository;
import br.com.spark.monolito.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public List<CartDto> findAll() {
        log.debug("Requet to get all Carts");
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }

    public CartDto create(final Long customerId) {
        if (this.getActiveCart(customerId) == null) {
            var customer = this.customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalStateException("The Customer does not exist!"));

            var order = this.orderService.create();
            var cart = new Cart(order, customer, CartStatus.NEW);
            return mapToDto(this.cartRepository.save(cart));
        }

        throw new IllegalStateException("There is already an active cart");
    }

    @Transactional(readOnly = true)
    public CartDto findById(final Long id) {
        log.debug("Request to get cart : {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }

    public void delete(final Long id) {
        log.debug("Request to delete Cart: {}", id);
        var cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id " + id));
        cart.setStatus(CartStatus.CANCELED);
        this.cartRepository.save(cart);
    }

    public CartDto getActiveCart(final Long customerId) {
        return Optional.ofNullable(this.cartRepository.findByStatusAndCustomerId(CartStatus.NEW, customerId))
                .filter(c -> !c.isEmpty())
                .filter(carts -> carts.size() == 1)
                .map(carts -> mapToDto(carts.get(0)))
                .orElse(null);
    }

    public static CartDto mapToDto(Cart cart) {

        if(cart == null) {
            return null;
        }

        return new CartDto(
                cart.getId(),
                cart.getOrder().getId(),
                CustomerService.maptoDto(cart.getCustomer()), cart.getStatus().name());
    }
}
