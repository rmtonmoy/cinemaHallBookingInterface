package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.UserSignInDto;
import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.UserStatus;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isAdminWithRightPassword(UserSignInDto userSignInDto) {
        Admin admin = adminRepository.findByEmail(userSignInDto.getEmail());
        return (admin != null && admin.getPassword().equals(userSignInDto.getPassword()));
    }

    @Override
    public boolean isActiveCustomerWithRightPassword(UserSignInDto userSignInDto) {
        Customer customer = customerRepository.findByEmail(userSignInDto.getEmail());

        if(customer == null){
            return  false;
        }


        if(customer.getPassword().equals(userSignInDto.getPassword()) && customer.getStatus() == UserStatus.Active){
            return true;
        }
        return false;
    }

    @Override
    public boolean isInactiveCustomerWithRightPassword(UserSignInDto userSignInDto) {
        Customer customer = customerRepository.findByEmail(userSignInDto.getEmail());

        if(customer == null){
            return false;
        }


        if(customer.getPassword().equals(userSignInDto.getPassword()) && customer.getStatus() == UserStatus.Inactive){
            return true;
        }
        return false;
    }
}
