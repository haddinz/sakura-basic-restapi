package com.sakura.resfullapi.service;

import com.sakura.resfullapi.models.entity.UserApp;
import com.sakura.resfullapi.models.repository.UserAppRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAppService implements UserDetailsService {
    @Autowired
    private UserAppRepo userAppRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAppRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("user with email '%s' not found", email)
                ));
    }

    public UserApp register(UserApp user) {
        boolean userExist = userAppRepo.findByEmail(user.getEmail()).isPresent();
        if(userExist) {
            throw new RuntimeException(
                    String.format("user with email '%s' already exist", user.getEmail())
            );
        }
        String passwordEncode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        return userAppRepo.save(user);
    }
}
