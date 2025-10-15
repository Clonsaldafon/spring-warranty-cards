package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.dto.user.CreateUserDto;
import ru.clonsaldafon.spring_warranty_cards.exception.ResourceNotFoundException;
import ru.clonsaldafon.spring_warranty_cards.model.Role;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User register(CreateUserDto createUserDto) {
        User user = new User(
                createUserDto.getEmail(),
                passwordEncoder.encode(createUserDto.getPassword()),
                createUserDto.getFullName(),
                Role.ROLE_USER
        );

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        userRepository.deleteByEmail(email);
    }
}
