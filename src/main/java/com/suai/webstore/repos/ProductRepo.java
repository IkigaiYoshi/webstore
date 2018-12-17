package com.suai.webstore.repos;

import com.suai.webstore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    Optional<Product> findById(Long id);
}
