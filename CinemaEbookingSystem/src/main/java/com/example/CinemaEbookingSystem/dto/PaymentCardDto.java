package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import java.util.List;

public class PaymentCardDto {
    private List<PaymentCard> cards;
    
    public PaymentCardDto(){}
    public PaymentCardDto(List<PaymentCard> cards) {
        this.cards = cards;
    }
    
    public void addCard(PaymentCard card) {
        cards.add(card);
    }
    
    public List<PaymentCard> getCards() {
        return cards;
    }
    
    public void setCards(List<PaymentCard> cards) {
        this.cards = cards;
    }
}
