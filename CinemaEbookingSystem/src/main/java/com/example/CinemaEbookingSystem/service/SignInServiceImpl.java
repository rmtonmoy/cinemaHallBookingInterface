package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.UserSignInDto;
import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.UserStatus;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isAdmin(UserSignInDto userSignInDto) {
        Admin admin = adminRepository.findByEmail(userSignInDto.getEmail());
        return (admin != null);
    }

    @Override
    public boolean isActiveCustomer(UserSignInDto userSignInDto) {
        Customer customer = customerRepository.findByEmail(userSignInDto.getEmail());

        if(customer == null){
            return  false;
        }

        if(customer.getStatus() == UserStatus.Active){
            return true;
        }
        return false;
    }

    @Override
    public boolean isInactiveCustomer(UserSignInDto userSignInDto) {
        Customer customer = customerRepository.findByEmail(userSignInDto.getEmail());

        if(customer == null){
            return false;
        }

        if(customer.getStatus() == UserStatus.Inactive){
            return true;
        }
        return false;
    }
}
