//Category Repository
package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}