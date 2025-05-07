package org.restaurantmanager.repositories;

import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long categoryId);
}
