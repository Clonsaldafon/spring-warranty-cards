package ru.clonsaldafon.spring_warranty_cards.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.user.CreateUserDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.UserMapper;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.security.CustomAuthenticationSuccessHandler;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;

import java.io.IOException;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

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
    public void register(@ModelAttribute @Valid CreateUserDto createUserDto, BindingResult bindingResult,
                         HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (bindingResult.hasErrors()) {
            request.getRequestDispatcher("/auth/register").forward(request, response);
            return;
        }

        User user = userService.register(createUserDto);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createUserDto.getEmail(),
                        createUserDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        successHandler.onAuthenticationSuccess(request, response, authentication);
    }
}
