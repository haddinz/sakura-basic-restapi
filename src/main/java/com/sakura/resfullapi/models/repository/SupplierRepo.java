package com.sakura.resfullapi.models.repository;

import com.sakura.resfullapi.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    Supplier findByEmail(String email);
    List<Supplier> findByNameContains(String name);
    List<Supplier> findByNameStartingWith(String prefix);
    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
