package org.restaurantmanager.services.user;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.models.Category;
import org.restaurantmanager.repositories.CategoryRepository;
import org.restaurantmanager.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(Category::getCategoryDto)
                .collect(Collectors.toList());
    }


}
