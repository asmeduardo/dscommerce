package com.asmeduardo.dscommerce.services;

import com.asmeduardo.dscommerce.dtos.ProductDTO;
import com.asmeduardo.dscommerce.dtos.ProductMinDTO;
import com.asmeduardo.dscommerce.mappers.ProductMapper;
import com.asmeduardo.dscommerce.models.Product;
import com.asmeduardo.dscommerce.repositories.ProductRepository;
import com.asmeduardo.dscommerce.services.exceptions.DatabaseException;
import com.asmeduardo.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(p -> productMapper.toDto(p));
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findByName(String name, Pageable pageable) {
        return productRepository.searchByName(name, pageable).map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            productMapper.updateProductFromDto(dto, product);
            return productMapper.toDto(productRepository.save(product));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
