package fr.tse.backendkairos.services;

import fr.tse.backendkairos.models.User;
import fr.tse.backendkairos.payload.request.CreateUserRequest;

public interface UserService{
    public User createUser(CreateUserRequest createUserRequest);
}
