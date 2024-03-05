package com.Momentique.Momentique.Resource;

import com.Momentique.Momentique.Services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRest {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent() throws Exception {
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(1000, "usd");
        return paymentIntent.getClientSecret();
    }
}

