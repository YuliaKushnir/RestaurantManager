package org.restaurantmanager.services.user;

import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.dto.ReservationDto;

import java.util.List;

public interface CustomerService {

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    List<ProductDto> getAllProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    ReservationDto postReservation(ReservationDto reservationDto);

    List<ReservationDto> getReservationsByUser(Long customerId);
}
