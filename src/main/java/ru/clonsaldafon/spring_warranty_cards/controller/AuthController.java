package ru.clonsaldafon.spring_warranty_cards.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clonsaldafon.spring_warranty_cards.dto.user.CreateUserDto;
import ru.clonsaldafon.spring_warranty_cards.dto.user.UserDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.UserMapper;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody CreateUserDto createUserDto) {
        User user = userService.register(createUserDto);
        return userMapper.toDto(user);
    }
}
