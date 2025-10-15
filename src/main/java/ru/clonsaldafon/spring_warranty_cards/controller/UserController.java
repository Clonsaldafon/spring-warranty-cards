package ru.clonsaldafon.spring_warranty_cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.user.UserDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.UserMapper;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return userMapper.toDto(user);
    }
}
