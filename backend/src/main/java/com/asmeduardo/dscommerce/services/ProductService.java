package com.asmeduardo.dscommerce.services;

import com.asmeduardo.dscommerce.dto.ProductDTO;
import com.asmeduardo.dscommerce.models.Product;
import com.asmeduardo.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }
}
