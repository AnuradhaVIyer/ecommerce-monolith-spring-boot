//Order Repository
package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
 List<Orders> findByUserUserId(Long userId);
}
