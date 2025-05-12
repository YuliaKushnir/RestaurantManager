package org.restaurantmanager.services.admin;

import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.dto.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    void deleteProduct(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;

    ProductDto getProductById(Long productId);

    List<ReservationDto> getReservations();

    ReservationDto changeReservationStatus(Long reservationId, String status);
}
