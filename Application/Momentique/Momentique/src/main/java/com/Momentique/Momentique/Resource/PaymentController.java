package com.Momentique.Momentique.Resource;

import com.Momentique.Momentique.Services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent() throws Exception {

        return stripeService.createPaymentIntent(1000, "usd"); // Exempelbelopp och valuta
    }
}

