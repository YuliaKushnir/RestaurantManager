package org.restaurantmanager.repositories;

import org.restaurantmanager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByTitleContaining(String title);
}
