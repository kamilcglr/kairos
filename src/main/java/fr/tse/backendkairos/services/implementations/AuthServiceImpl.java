package fr.tse.backendkairos.services.implementations;

import fr.tse.backendkairos.models.ERole;
import fr.tse.backendkairos.models.User;
import fr.tse.backendkairos.payload.request.LoginRequest;
import fr.tse.backendkairos.payload.request.SignupRequest;
import fr.tse.backendkairos.payload.response.JwtResponse;
import fr.tse.backendkairos.payload.response.MessageResponse;
import fr.tse.backendkairos.repository.UserRepository;
import fr.tse.backendkairos.security.jwt.JwtUtils;
import fr.tse.backendkairos.security.services.UserDetailsImpl;
import fr.tse.backendkairos.services.interfaces.AuthService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList().get(0);

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                role);
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        ERole strRole = ERole.valueOf(signUpRequest.getRole().toUpperCase(Locale.ROOT));

        user.setRole(strRole);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

}
