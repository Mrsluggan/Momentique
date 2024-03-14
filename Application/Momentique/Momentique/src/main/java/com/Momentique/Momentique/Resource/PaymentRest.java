// package com.Momentique.Momentique.Resource;

// import com.Momentique.Momentique.Models.ChargeRequest;
// import com.Momentique.Momentique.Services.StripeService;
// import com.stripe.exception.StripeException;
// import com.stripe.model.Charge;
// import com.stripe.model.PaymentIntent;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @CrossOrigin(origins = "*")
// public class PaymentRest {

//     @Autowired
//     private StripeService stripeService;

//     @PostMapping("/create-payment-intent")
//     public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> payload) {
//         try {
//             // Anta att 'amount' och 'currency' skickas i payload
//             long amount = Long.parseLong(payload.get("amount").toString());
//             String currency = payload.get("currency").toString();

//             PaymentIntent paymentIntent = stripeService.createPaymentIntent(amount, currency);
//             return ResponseEntity.ok(Map.of("clientSecret", paymentIntent.getClientSecret()));
//         } catch (StripeException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//         }
//     }

//     @PostMapping("/charge")
//     public ResponseEntity<?> charge(@RequestBody ChargeRequest chargeRequest) {
//         try {
//             Charge charge = stripeService.chargeCreditCard(chargeRequest.getToken(), chargeRequest.getAmount(), "sek");
//             return ResponseEntity.ok(Map.of("status", charge.getStatus()));
//         } catch (StripeException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
//         }
//     }

//     @PostMapping("/create-checkout-session")
//     public ResponseEntity<?> createCheckoutSession() {
//         try {
//             com.stripe.model.checkout.Session session = stripeService.createCheckoutSession("https://example.com/success", "https://example.com/cancel");
//             return ResponseEntity.ok(Map.of("url", session.getUrl()));
//         } catch (StripeException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//         }
//     }
// }
