package com.sakura.resfullapi.controller;

import com.sakura.resfullapi.dto.ResponseData;
import com.sakura.resfullapi.dto.SearchDto;
import com.sakura.resfullapi.dto.SupplierDto;
import com.sakura.resfullapi.models.entity.Supplier;
import com.sakura.resfullapi.service.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDto supplierDto, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

//        Supplier supplier = new Supplier();
//        supplier.setName(supplierDto.getName());
//        supplier.setAddress(supplierDto.getName());
//        supplier.setEmail(supplierDto.getEmail());
//        dari pada memasukin persatu semua data, lebih baik gunakan modelMapper

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findById(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDto supplierDto, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/email")
    public Supplier findSupplierByEmail(@RequestBody SearchDto searchDto) {
        return supplierService.findSupplierByEmail(searchDto.getSearchKey());
    }

    @PostMapping("/search/name")
    public List<Supplier> findSupplierByName(@RequestBody SearchDto searchDto) {
        return supplierService.findSupplierByName(searchDto.getSearchKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Supplier> findSupplierByNameStartWith(@RequestBody SearchDto searchDto) {
        return supplierService.findSupplierByNameStartWith(searchDto.getSearchKey());
    }

    @PostMapping("/search/nameoremail")
    public List<Supplier> findSupplierByNameOrEmail(@RequestBody SearchDto searchDto) {
        return supplierService.findSupplierByNameOrEmail(searchDto.getSearchKey(), searchDto.getOtherSearchKey());
    }
}
