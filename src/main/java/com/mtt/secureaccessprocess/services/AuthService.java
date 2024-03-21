package com.mtt.secureaccessprocess.services;

import com.mtt.secureaccessprocess.dto.SignupRequest;
import com.mtt.secureaccessprocess.entities.User;

public interface AuthService {
    User createUser(SignupRequest signupRequest);

}
