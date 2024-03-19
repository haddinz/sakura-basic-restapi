package com.sakura.resfullapi.models.repository;

import com.sakura.resfullapi.models.entity.UserApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserAppRepo extends PagingAndSortingRepository<UserApp, Long>, CrudRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
}
