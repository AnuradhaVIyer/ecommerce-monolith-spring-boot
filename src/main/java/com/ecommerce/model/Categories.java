//Category Entity
package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 100, message = "Category name must be within 100 characters")
    private String categoryName;

    public Categories() {}

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}