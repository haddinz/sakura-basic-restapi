package com.sakura.resfullapi.service;

import com.sakura.resfullapi.models.entity.Supplier;
import com.sakura.resfullapi.models.repository.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);

        if(!supplier.isPresent()) {
            return null;
        }

        return supplier.get();
    }

    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public void removeOne(Long id) {
        supplierRepo.deleteById(id);
    }

    public Supplier findSupplierByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }

    public List<Supplier> findSupplierByName(String name) {
        return supplierRepo.findByNameContains(name);
    }

    public List<Supplier> findSupplierByNameStartWith(String prefix) {
        return supplierRepo.findByNameStartingWith(prefix);
    }

    public List<Supplier> findSupplierByNameOrEmail(String name, String email) {
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }
}
