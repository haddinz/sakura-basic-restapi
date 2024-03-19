package com.sakura.resfullapi.service;

import com.sakura.resfullapi.models.entity.Category;
import com.sakura.resfullapi.models.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        if(category.getId() != null) {
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            category = currentCategory;
        }
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> findCategory = categoryRepo.findById(id);
        if(!findCategory.isPresent()){
            return null;
        }
        return findCategory.get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findCategoryByName(String name, Pageable pageable) {
        return categoryRepo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveAll(Iterable<Category> categories) {
        return categoryRepo.saveAll(categories);
    }
}
