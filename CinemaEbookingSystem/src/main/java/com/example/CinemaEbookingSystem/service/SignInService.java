package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.UserSignInDto;
import com.example.CinemaEbookingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public interface SignInService {
    boolean isAdminWithRightPassword(UserSignInDto userSignInDto);
    boolean isActiveCustomerWithRightPassword(UserSignInDto userSignInDto);
    boolean isInactiveCustomerWithRightPassword(UserSignInDto userSignInDto);
}
