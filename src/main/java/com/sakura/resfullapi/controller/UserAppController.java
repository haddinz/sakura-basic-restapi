package com.sakura.resfullapi.controller;

import com.sakura.resfullapi.dto.ResponseData;
import com.sakura.resfullapi.dto.UserAppDto;
import com.sakura.resfullapi.models.entity.UserApp;
import com.sakura.resfullapi.service.UserAppService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAppController {

    @Autowired
    private UserAppService userAppService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<UserApp>> register(@RequestBody UserAppDto userAppDto, Errors errors) {
        ResponseData<UserApp> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.getMessages().add("something wrong when user register");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        UserApp userApp = modelMapper.map(userAppDto, UserApp.class);
        responseData.setPayload(userAppService.register(userApp));
        responseData.setStatus(true);
        responseData.getMessages().add("user registered successfully");
        return ResponseEntity.ok(responseData);
    }
}
