package com.example.product.services;

import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import com.example.product.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServices {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepo productRepo;

    public List<ProductDTO> getAllProducts(){
        List<Product> allProducts = productRepo.findAll();
        return modelMapper.map(allProducts, new TypeToken<List<Product>>(){}.getType());
    }

    public ProductDTO getProductsByProductId(int productId){
       Product product = productRepo.findProductByProductId(productId);
        return modelMapper.map(product,new TypeToken<ProductDTO>(){}.getType());
    }

    public ProductDTO saveProduct(ProductDTO productDTO){
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public boolean deleteProduct(ProductDTO productDTO){
        productRepo.delete(modelMapper.map(productDTO, Product.class));
        return true;
    }
}
