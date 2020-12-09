package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.utils.PaypalPaymentIntent;
import com.dinhquangduy.electronic.utils.PaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    public Payment createPayment(Double total, String currency, PaypalPaymentMethod method, PaypalPaymentIntent intent, String description, String cancelUrl,
            String successUrl) throws PayPalRESTException;
    
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
