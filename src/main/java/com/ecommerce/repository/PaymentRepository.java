package com.ecommerce.repository;

//Payment Repository
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Long> {}