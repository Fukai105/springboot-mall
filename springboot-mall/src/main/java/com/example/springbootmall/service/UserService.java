package com.example.springbootmall.service;

import com.example.springbootmall.dto.UserRegisterRequest;
import com.example.springbootmall.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    Integer register(@RequestBody @Valid UserRegisterRequest userRegisterRequest);


    User getUserById(Integer userId);
}
