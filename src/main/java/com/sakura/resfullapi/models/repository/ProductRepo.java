package com.sakura.resfullapi.models.repository;

import com.sakura.resfullapi.models.entity.Product;
import com.sakura.resfullapi.models.entity.Supplier;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT product FROM Product product WHERE product.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT product FROM Product product WHERE product.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT product FROM Product product WHERE :supplier MEMBER of product.supliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
