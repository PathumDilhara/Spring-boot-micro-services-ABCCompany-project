package com.example.product.repo;

import com.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT * FROM product WHERE product_id=?1", nativeQuery = true)
    Product findProductByProductId(int itemId);
}
