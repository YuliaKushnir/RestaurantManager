package org.restaurantmanager.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryDto {
    private Long id;

    private String title;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;
}
