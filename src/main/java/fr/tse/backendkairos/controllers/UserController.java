package fr.tse.backendkairos.controllers;

import fr.tse.backendkairos.models.User;
import fr.tse.backendkairos.payload.request.CreateUserRequest;
import fr.tse.backendkairos.repository.RoleRepository;
import fr.tse.backendkairos.repository.UserRepository;
import fr.tse.backendkairos.security.jwt.JwtUtils;
import fr.tse.backendkairos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        final User createdUser = userService.createUser(createUserRequest);
        return ResponseEntity.ok(createdUser);
    }
}
