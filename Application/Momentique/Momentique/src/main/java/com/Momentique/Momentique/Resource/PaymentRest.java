package com.Momentique.Momentique.Resource;

import com.Momentique.Momentique.Services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.checkout.Session;

@RestController
public class PaymentRest {

    @Autowired
    private StripeService stripeService;

    // Endpoint payment intent 
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent() throws Exception {
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(1000, "sek");
        return paymentIntent.getClientSecret();
    }

    // Endpoint checkout Session
    @PostMapping("/create-checkout-session")
    public String createCheckoutSession() throws Exception {
        Session session = stripeService.createCheckoutSession();
        return session.getUrl(); // Returnerar URL till Checkout Session för omdirigering
    }
}

