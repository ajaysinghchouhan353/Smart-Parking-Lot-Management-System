package org.splms.payment;

public class CardPaymentProcessor implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processed card payment of ₹" + amount);
        return true;
    }
}