package com.sakura.resfullapi.models.repository;

import com.sakura.resfullapi.models.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
    Page<Category> findByNameContains(String name, Pageable pageable);
}
