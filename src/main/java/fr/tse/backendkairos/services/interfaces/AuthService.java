package fr.tse.backendkairos.services.interfaces;

import fr.tse.backendkairos.payload.request.LoginRequest;
import fr.tse.backendkairos.payload.request.SignupRequest;
import fr.tse.backendkairos.payload.response.JwtResponse;
import fr.tse.backendkairos.payload.response.MessageResponse;

public interface AuthService {

    public JwtResponse authenticateUser(LoginRequest loginRequest);

    public MessageResponse registerUser(SignupRequest signUpRequest);
}
