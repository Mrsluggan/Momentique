package com.Momentique.Momentique.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.apiKey;
    }

    public StripeService() {
        Stripe.apiKey = this.apiKey;
    }

    public PaymentIntent createPaymentIntent(long amount, String currency) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .build();

        return PaymentIntent.create(params);
    }

    // Endpoint charge - skapar betalningar, hantera kunder, abonnemang
    public Charge chargeCreditCard(String token, double amount) throws StripeException {
        ChargeCreateParams params =
            ChargeCreateParams.builder()
                .setAmount((long) (amount * 100)) 
                .setCurrency("sek")
                .setDescription("Exempel betalning")
                .setSource(token)
                .build();

        return Charge.create(params);
    }

    // Checkout Session
    public Session createCheckoutSession() throws StripeException {
        SessionCreateParams params = SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("https://example.com/success")
            .setCancelUrl("https://example.com/cancel")
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("sek")
                            .setUnitAmount(1000L) 
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("Produktens namn här")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .build();

        return Session.create(params);
    }
}
