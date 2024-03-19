package com.sakura.resfullapi.service;

import com.sakura.resfullapi.models.entity.Product;
import com.sakura.resfullapi.models.entity.Supplier;
import com.sakura.resfullapi.models.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> temp = productRepo.findById(id);
        return temp.orElse(null);
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if(product == null) {
            throw new RuntimeException("Product id "+productId+" is not found");
        }
        product.getSupliers().add(supplier);
        save(product);
    }

    public Product findProductByName(String name) {
        return productRepo.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name) {
        return productRepo.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findProductByCategoryId(Long categoryId) {
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findProductBySupplier(Long id) {
        Supplier supplier = supplierService.findOne(id);
        if(supplier == null) {
            return new ArrayList<>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
}
