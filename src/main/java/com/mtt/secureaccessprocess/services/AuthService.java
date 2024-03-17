package com.mtt.secureaccessprocess.services;

import com.mtt.secureaccessprocess.dto.SignupRequest;
import com.mtt.secureaccessprocess.entities.Customer;

public interface AuthService {
    Customer createCustomer(SignupRequest signupRequest);

}
