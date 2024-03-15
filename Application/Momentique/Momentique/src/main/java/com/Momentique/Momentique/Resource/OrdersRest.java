package com.Momentique.Momentique.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Momentique.Momentique.Models.Orders;
import com.Momentique.Momentique.Repositories.OrderRepository;
import com.Momentique.Momentique.Repositories.Products.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.model.LineItemCollection;

@RestController
@CrossOrigin(origins = "*")
public class OrdersRest {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    // private final PPRepository ppRepository;

    public OrdersRest(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("success/{CHECKOUT_SESSION_ID}")
    public RedirectView getCust(@PathVariable("CHECKOUT_SESSION_ID") String checkoutId, @RequestParam(required = false) boolean canceled, RedirectAttributes ra) throws StripeException {
        
        Stripe.apiKey = "sk_test_51OoiqDF3bq9e58MhuJUSymuIlR3IVHXrzUIbxxPyNUdmLb2bLOjiYluaRajx1oFHivrdhWKUkJVBe6hcKUVWFkQr00jHP39g4d";

        String redirectUrl = "http://127.0.0.1:5501/afterPay.html";

        if (canceled) {
            // Om betalningen avbröts av användaren
            redirectUrl += "?status=denied";
        } else {
            // Hämta sessionen med det angivna checkoutId
            
            Session session = Session.retrieve(checkoutId);

            LineItemCollection lineItems = session.listLineItems();

            List<String> listOfProducts = new ArrayList<>();
            double totalPrice = 0;


            for (int i=0; i<lineItems.getData().size(); i++) {
                double price = (double)lineItems.getData().get(i).getAmountTotal()/100;
                int quantity = lineItems.getData().get(i).getQuantity().intValue();
    
                JSONObject j = new JSONObject();
                j.put("quantity", String.valueOf(quantity));
                j.put("name", lineItems.getData().get(i).getDescription());
                j.put("price", String.valueOf(price));

        
                listOfProducts.add(j.toString());
                totalPrice = totalPrice + price;
            }


            Orders order = new Orders();
            order.setOrderEmail(session.getCustomerDetails().getEmail());
            order.setOrderName(session.getCustomerDetails().getName());
            order.setDeliveryAdress(session.getCustomerDetails().getAddress().getLine1() + ", " 
            + session.getCustomerDetails().getAddress().getPostalCode() + ", "
            + session.getCustomerDetails().getAddress().getCity() + ", "
            + session.getCustomerDetails().getAddress().getCountry());
            order.setProducts(listOfProducts);
            order.setTotalCost(totalPrice);

            UUID temporaryUUID = UUID.randomUUID();
            order.setTemporaryUUID(temporaryUUID);
            orderRepository.save(order);

            ra.addAttribute(temporaryUUID);

            // Kontrollera om betalningen var framgångsrik baserat på sessionens status
            String paymentStatus = session.getPaymentStatus();

            if ("paid".equals(paymentStatus)) {
                redirectUrl += "?status=success";
            } else {
                redirectUrl += "?status=denied";
            }
        }
        return new RedirectView(redirectUrl);
    }

    @GetMapping("orders/order/{temporaryUUID}")
    public ResponseEntity<List<Orders>> findOrderByTU(@PathVariable("temporaryUUID") UUID TU) {

        List<Orders> result = orderRepository.searchOrderByTU(TU);
        if (result != null) {
            if(TU != null) {
            orderRepository.eraseTemporaryUUID(null, TU);
            return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.status(404).build();

    }


    @GetMapping("orders")
    public Iterable<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    // Skapa order, denna är WIP. Vi måste lista ut hur vi ska ta orden fron front till oss
    // @GetMapping("orders/newOrder")
    // public Orders createOrder() {
    //     orderRepository.deleteAll();
    //     Orders order = new Orders();
    //     Iterable<Product> products = productRepository.findAll();
    //     long totalCost = 0;
    //     for (Product product : products) {
    //         System.out.println(product.getPrice());
    //         totalCost += product.getPrice();
    //         order.setTotalCost(totalCost);
    //         order.getProducts().add(product);
    //     }

    //     orderRepository.save(order);

    //     return order;
    // }

    // Hitta order

    @GetMapping("orders/{orderId}")
    public ResponseEntity<Optional<Orders>> findOrderById(@PathVariable("orderId") Long orderId) {
        Optional<Orders> result = orderRepository.findById(orderId);
        if (result != null) {
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.status(404).build();

    }

    // Ta bort order






}
