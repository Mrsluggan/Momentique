package com.Momentique.Momentique.Services;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    public StripeService() {
        Stripe.apiKey = this.apiKey;
    }

    public PaymentIntent createPaymentIntent(long amount, String currency) throws Exception {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .build();

        return PaymentIntent.create(params);
    }
}
