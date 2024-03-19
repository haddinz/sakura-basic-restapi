package com.sakura.resfullapi.controller;

import com.sakura.resfullapi.dto.CategoryDto;
import com.sakura.resfullapi.dto.ResponseData;
import com.sakura.resfullapi.dto.SearchDto;
import com.sakura.resfullapi.models.entity.Category;
import com.sakura.resfullapi.service.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDto categoryDto, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody Category category, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData) ;
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        categoryService.removeOne(id);
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findCategoryByName(@RequestBody SearchDto searchDto, @PathVariable("size") int size, @PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findCategoryByName(searchDto.getSearchKey(), pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findCategoryByName(@RequestBody SearchDto searchDto, @PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("sort") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if(sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findCategoryByName(searchDto.getSearchKey(), pageable);
    }

    @PostMapping("/saveall")
    public ResponseEntity<ResponseData<Iterable<Category>>> saveAll(@RequestBody Category[] categories) {
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        responseData.setPayload(categoryService.saveAll(Arrays.asList(categories)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);

//        kalau mau tambah validasi silahkan
    }
}
