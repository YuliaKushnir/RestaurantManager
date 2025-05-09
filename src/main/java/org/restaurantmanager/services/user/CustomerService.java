package org.restaurantmanager.services.user;

import org.restaurantmanager.dto.CategoryDto;

import java.util.List;

public interface CustomerService {

    List<CategoryDto> getAllCategories();
}
