package com.sena.Api_SpringBoot.repository;

import com.sena.Api_SpringBoot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
