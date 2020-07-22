package com.backend.backend.controller;

import com.backend.backend.exception.UsersNotFoundException;
import com.backend.backend.model.Users;
import com.backend.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UsersRepository userRepository;

    // Get All Notes
    @GetMapping("/users")
    public List<Users> getAllNotes() {
        return userRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/users")
    public Users createNote(@Valid @RequestBody Users user) {
        return userRepository.save(user);
    }

}
