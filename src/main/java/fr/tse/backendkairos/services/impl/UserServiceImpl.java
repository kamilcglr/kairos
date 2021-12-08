package fr.tse.backendkairos.services.impl;

import fr.tse.backendkairos.models.ERole;
import fr.tse.backendkairos.models.Role;
import fr.tse.backendkairos.models.User;
import fr.tse.backendkairos.payload.request.CreateUserRequest;
import fr.tse.backendkairos.repository.RoleRepository;
import fr.tse.backendkairos.repository.UserRepository;
import fr.tse.backendkairos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public User createUser(CreateUserRequest createUserRequest) {
        User user = new User(
                createUserRequest.getEmail(),
                encoder.encode(createUserRequest.getPassword()));

        String strRole = createUserRequest.getRole();
        Role role;

        if (strRole == null) {
            role = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            switch (strRole) {
                case "admin" -> {
                    role = roleRepository.findByName(ERole.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
                case "manager" -> {
                    role = roleRepository.findByName(ERole.MANAGER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
                default -> {
                    role = roleRepository.findByName(ERole.USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
            }
        }

        user.setRole(role);
        return this.userRepository.save(user);
    }
}
