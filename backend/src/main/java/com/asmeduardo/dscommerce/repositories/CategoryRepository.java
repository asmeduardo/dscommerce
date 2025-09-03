package com.asmeduardo.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asmeduardo.dscommerce.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
