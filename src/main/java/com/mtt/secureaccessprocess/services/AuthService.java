package com.mtt.secureaccessprocess.services;

import com.mtt.secureaccessprocess.dto.SignupRequest;

public interface AuthService {
    boolean createCustomer(SignupRequest signupRequest);

}
