package org.restaurantmanager.models;

import jakarta.persistence.*;
import lombok.Data;
import org.restaurantmanager.dto.CategoryDto;

@Entity
@Data
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public CategoryDto getCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setTitle(title);
        categoryDto.setDescription(description);
        categoryDto.setReturnedImg(img);

        return categoryDto;
    }

}
