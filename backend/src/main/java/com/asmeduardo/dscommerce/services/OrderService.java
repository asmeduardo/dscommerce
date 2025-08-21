package com.asmeduardo.dscommerce.services;

import com.asmeduardo.dscommerce.dtos.OrderDTO;
import com.asmeduardo.dscommerce.models.Order;
import com.asmeduardo.dscommerce.repositories.OrderRepository;
import com.asmeduardo.dscommerce.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }
}