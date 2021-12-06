package com.example.CinemaEbookingSystem.model;

public class CartTotal {
    float Subtotal;
    float SalesTax;
    float BookingFee;
    float OrderTotal;
    float DiscountedTotal;
    String OrderTotalM100;

    public CartTotal() {
    }

    public CartTotal(float subtotal, float salesTax, float bookingFee, float orderTotal) {
        this.Subtotal = subtotal;
        this.SalesTax = salesTax;
        this.BookingFee = bookingFee;
        this.OrderTotal = orderTotal;
        int OrderTotalInt100 = (int) (OrderTotal*100);
        this.DiscountedTotal = orderTotal;
        this.OrderTotalM100 = String.valueOf(OrderTotalInt100);
    }

    public float getDiscountedTotal() {
        return DiscountedTotal;
    }

    public void setDiscountedTotal(float discountedTotal) {
        DiscountedTotal = discountedTotal;
    }

    public String getOrderTotalM100() {
        return OrderTotalM100;
    }

    public void setOrderTotalM100(String orderTotalM100) {
        OrderTotalM100 = orderTotalM100;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public float getSalesTax() {
        return SalesTax;
    }

    public void setSalesTax(float salesTax) {
        SalesTax = salesTax;
    }

    public float getBookingFee() {
        return BookingFee;
    }

    public void setBookingFee(float bookingFee) {
        BookingFee = bookingFee;
    }

    public float getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        OrderTotal = orderTotal;
    }
}
