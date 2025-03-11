package com.ecommerce.repository;

//OrderDetail Repository
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {}
