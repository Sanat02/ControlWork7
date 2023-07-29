package com.example.controlwork.controller;

import com.example.controlwork.configuration.dto.UserDto;
import com.example.controlwork.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public HttpStatus registerUser(@RequestBody @Valid UserDto userDto) {
        userService.saveUser(userDto);
        return HttpStatus.OK;
    }
    @PostMapping("/login")
    public String LoginUser(@RequestBody @Valid UserDto userDto) {
        return userService.loginUser(userDto);

    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
}
