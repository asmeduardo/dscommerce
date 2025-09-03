package com.asmeduardo.dscommerce.services;

import com.asmeduardo.dscommerce.dtos.OrderDTO;
import com.asmeduardo.dscommerce.dtos.OrderItemDTO;
import com.asmeduardo.dscommerce.models.Order;
import com.asmeduardo.dscommerce.models.OrderItem;
import com.asmeduardo.dscommerce.models.OrderStatus;
import com.asmeduardo.dscommerce.models.Product;
import com.asmeduardo.dscommerce.models.User;
import com.asmeduardo.dscommerce.repositories.OrderItemRepository;
import com.asmeduardo.dscommerce.repositories.OrderRepository;
import com.asmeduardo.dscommerce.repositories.ProductRepository;
import com.asmeduardo.dscommerce.services.exceptions.ResourceNotFoundException;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}