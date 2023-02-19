package com.asmeduardo.dscommerce.repositories;

import com.asmeduardo.dscommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
