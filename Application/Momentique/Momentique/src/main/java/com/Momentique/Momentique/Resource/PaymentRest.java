package com.Momentique.Momentique.Resource;

import com.Momentique.Momentique.Models.ChargeRequest;
import com.Momentique.Momentique.Services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.checkout.Session;

@RestController
@CrossOrigin(origins = "*")
public class PaymentRest {

    @Autowired
    private StripeService stripeService;

    public PaymentRest(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    // Endpoint payment intent 
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent() throws StripeException {
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(1000, "sek");
        return paymentIntent.getClientSecret();
    }
    

    @PostMapping("/charge")
    public String charge(@RequestBody ChargeRequest chargeRequest) {
        try {
            stripeService.chargeCreditCard(chargeRequest.getToken(), chargeRequest.getAmount());
            return "Betalning genomförd";
        } catch (StripeException e) {
            return "Betalning misslyckades: " + e.getMessage();
        }
    }

    // Endpoint checkout Session
    @PostMapping("/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(HttpServletRequest request) {
        try {
            Session session = stripeService.createCheckoutSession();
            // Använd ResponseEntity för att skicka tillbaka en strukturerad respons
            return ResponseEntity.ok(Map.of("url", session.getUrl()));
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


}

