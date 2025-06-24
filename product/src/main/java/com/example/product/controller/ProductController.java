package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {

        return productServices.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductsByProductId(@PathVariable int productId) {
        System.out.println("######## Received request for product id={}"+ productId);
        return productServices.getProductsByProductId(productId);
    }

    @PostMapping("/saveProduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productServices.saveProduct(productDTO);
    }

    @PutMapping("/updateProduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productServices.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteProduct")
    public boolean deleteProduct(@RequestBody ProductDTO productDTO) {
        return productServices.deleteProduct(productDTO);
    }

}
