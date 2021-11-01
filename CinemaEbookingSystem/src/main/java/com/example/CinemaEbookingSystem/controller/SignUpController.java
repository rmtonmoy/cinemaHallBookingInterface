package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.service.CustomerService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class SignUpController {

    private CustomerService customerService;

    public SignUpController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @ModelAttribute("customer")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("customer_payment_card_1")
    public PaymentCardDto paymentCardDto() {
        return new PaymentCardDto();
    }
    @ModelAttribute("customer_payment_card_2") // why can't i just reuse the original method? >:^| -james
    public PaymentCardDto paymentCardDto2() {
        return new PaymentCardDto();
    }
    @ModelAttribute("customer_payment_card_3")
    public PaymentCardDto paymentCardDto3() {
        return new PaymentCardDto();
    }

    @GetMapping(path = "/signup")
    public String showRegistrationForm(Model model) {
        PaymentCardDto cardsForm = new PaymentCardDto();
        model.addAttribute("form", cardsForm);
        return "signup";
    }

    @PostMapping(path = "/signup")
    public String registerUserAccount(@ModelAttribute("customer") UserRegistrationDto registrationDto,
        @ModelAttribute("customer_payment_card") PaymentCardDto paymentCardDto) {
        
        System.out.println(registrationDto.getFirstName());
        System.out.println(registrationDto.isIsRegistered());
        
        // stupid code inbound, but it's 2:38 am and i really cba -james
        /*List<PaymentCardDto> cards = new ArrayList<>(3);
        if (!isCardInvalid(paymentCardDto1)) {
            cards.add(paymentCardDto1);
        }
        if (!isCardInvalid(paymentCardDto2)) {
            cards.add(paymentCardDto2);
        }
        if (!isCardInvalid(paymentCardDto2)) {
            cards.add(paymentCardDto3);
        }*/
        for (PaymentCard card : paymentCardDto.getCards()) {
            System.out.println(card.getCardHolder() + " " + !isCardInvalid(card));
        }
        List<PaymentCard> cards = paymentCardDto.getCards();
        for (int i = 2; i >= 0; i--) {
            PaymentCard card = cards.get(i);
            if (isCardInvalid(card)) {
                cards.remove(i);
            }
        }
        
        boolean newCustomer = customerService.save(registrationDto, paymentCardDto);
        if(newCustomer == true)
        {
            return "redirect:/signup?success";
        }
        else
        {
            return "redirect:/signup?failure";
        }
    }
    
    private boolean isCardInvalid(PaymentCard card) {
        String cardNumber     = card.getCardNumber();
        String securityCode   = card.getSecurityCode();
        String cardHolder     = card.getCardHolder();
        String cardType       = card.getCardType();
        String expirationDate = card.getExpirationDate();
        String billingAddress = card.getBillingAddress();
        
        return (cardNumber     == null || cardNumber.equals(""))
            || (securityCode   == null || securityCode.equals(""))
            || (cardHolder     == null || cardHolder.equals(""))
            || (cardType       == null || cardType.equals(""))
            || (expirationDate == null || expirationDate.equals(""))
            || (billingAddress == null || billingAddress.equals(""));
    }
}
