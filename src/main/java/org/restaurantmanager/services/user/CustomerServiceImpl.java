package org.restaurantmanager.services.user;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.dto.ReservationDto;
import org.restaurantmanager.enums.ReservationStatus;
import org.restaurantmanager.models.Category;
import org.restaurantmanager.models.Product;
import org.restaurantmanager.models.Reservation;
import org.restaurantmanager.models.User;
import org.restaurantmanager.repositories.CategoryRepository;
import org.restaurantmanager.repositories.ProductRepository;
import org.restaurantmanager.repositories.ReservationRepository;
import org.restaurantmanager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(Category::getCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository
                .findAllByTitleContaining(title)
                .stream()
                .map(Category::getCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository
                .findAllByCategoryId(categoryId)
                .stream()
                .map(Product::getProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository
                .findAllByCategoryIdAndNameContaining(categoryId, title)
                .stream()
                .map(Product::getProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        if(optionalUser.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Reservation createdReservation = reservationRepository.save(reservation);

            ReservationDto createdReservationDto = new ReservationDto();
            createdReservationDto.setId(createdReservation.getId());

            return createdReservationDto;
        }

        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }


}
