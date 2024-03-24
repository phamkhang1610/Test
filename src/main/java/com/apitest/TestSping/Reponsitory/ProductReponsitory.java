package com.apitest.TestSping.Reponsitory;

import com.apitest.TestSping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReponsitory extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productname);
}
