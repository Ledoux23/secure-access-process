package com.mtt.secureaccessprocess.services;

import com.mtt.secureaccessprocess.dto.SignupRequest;
import com.mtt.secureaccessprocess.entities.Customer;
import com.mtt.secureaccessprocess.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean createCustomer(SignupRequest signupRequest) {
        //check if customer already exist
        if(customerRepository.existsByEmail(signupRequest.getEmail())){
            return false;
        }

        //Hash de password before saving
        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest, customer);

        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);
        customerRepository.save(customer);
        return true;
        //Customer createdCustomer = customerRepository.save(customer);
        //customer.setId(createdCustomer.getId());
        //return customer;
    }
}
















