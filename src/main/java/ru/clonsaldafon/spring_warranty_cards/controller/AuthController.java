package ru.clonsaldafon.spring_warranty_cards.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.user.CreateUserDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.UserMapper;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("createUserDto", new CreateUserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("createUserDto") @Valid CreateUserDto createUserDto,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        User user = userService.register(createUserDto);
        return "redirect:/auth/login";
    }
}
