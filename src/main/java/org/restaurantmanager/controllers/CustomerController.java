package org.restaurantmanager.controllers;


import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.dto.ReservationDto;
import org.restaurantmanager.services.jwt.UserService;
import org.restaurantmanager.services.user.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = customerService.getAllCategories();
        if(categoryDtoList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDto> categoryDtoList = customerService.getAllCategoriesByTitle(title);
        if(categoryDtoList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = customerService.getAllProductsByCategory(categoryId);

        if(productDtoList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = customerService.getProductsByCategoryAndTitle(categoryId, title);
        if(productDtoList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto) throws IOException {
        ReservationDto createdReservationDto = customerService.postReservation(reservationDto);

        if(createdReservationDto == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservationDto);
    }

    @GetMapping("/reservations/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Long customerId){
        List<ReservationDto> reservationDtos = customerService.getReservationsByUser(customerId);

        if(reservationDtos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }


}
