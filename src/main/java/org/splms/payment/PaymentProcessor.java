package org.splms.payment;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}