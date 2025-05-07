package org.restaurantmanager.services.admin;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.restaurantmanager.dto.CategoryDto;
import org.restaurantmanager.dto.ProductDto;
import org.restaurantmanager.models.Category;
import org.restaurantmanager.models.Product;
import org.restaurantmanager.repositories.CategoryRepository;
import org.restaurantmanager.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());

        Category createdCategory = categoryRepository.save(category);

        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());


        return createdCategoryDto;
    }

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
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isPresent()) {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);

            product.setImg(productDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());

            Product createdProduct = productRepository.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(createdProduct.getId());
            return createdProductDto;
        }

        return null;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository
                .findAllByCategoryId(categoryId)
                .stream()
                .map(Product::getProductDto)
                .collect(Collectors.toList());
    }


}
