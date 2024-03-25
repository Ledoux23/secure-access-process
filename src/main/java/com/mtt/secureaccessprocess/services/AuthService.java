package com.mtt.secureaccessprocess.services;

import com.mtt.secureaccessprocess.dto.SignupRequest;
import com.mtt.secureaccessprocess.entities.User;

import java.util.List;

public interface AuthService {
    User createUser(SignupRequest signupRequest);
    List<User> getAllUsers();
    void deleteUserById(Long userId);
}
