package com.backend.backend.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.backend.backend.exception.AppException;
import com.backend.backend.model.Users;
import com.backend.backend.payload.ApiResponce;
import com.backend.backend.payload.JwtAuthenticateResponce;
import com.backend.backend.payload.LoginRequest;
import com.backend.backend.payload.RegisterRequest;
import com.backend.backend.repository.UsersRepository;
import com.backend.backend.security.JwtTokenUtiliy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.backend.backend.security.UserDetailsPrincipals;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsersRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtiliy tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()

                )
        );
        //System.out.println(loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserDetailsPrincipals Userdetails = (UserDetailsPrincipals) authentication.getPrincipal();
      //  List<String> role = Userdetails.getAuthorities();
        return ResponseEntity.ok(new JwtAuthenticateResponce(jwt, Userdetails.getId(),Userdetails.getUsername(),Userdetails.getEmail(),

                                        Userdetails.getAuthorities(),Userdetails.getZipcode()));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponce(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponce(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Users user = new Users(signUpRequest.getUsername(),signUpRequest.getPassword(),signUpRequest.getUser_role(),signUpRequest.getEmail(),signUpRequest.getZipcode());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponce(true, "User registered successfully"));
    }
    }
