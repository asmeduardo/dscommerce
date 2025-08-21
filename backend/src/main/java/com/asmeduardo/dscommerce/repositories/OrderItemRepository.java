package com.asmeduardo.dscommerce.repositories;

import com.asmeduardo.dscommerce.models.OrderItem;
import com.asmeduardo.dscommerce.models.OrderItemPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
