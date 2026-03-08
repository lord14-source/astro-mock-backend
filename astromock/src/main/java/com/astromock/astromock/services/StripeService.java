package com.astromock.astromock.services;


import com.astromock.astromock.model.ProductRequest;
import com.astromock.astromock.model.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripeResponse checkoutProduct(ProductRequest productRequest) throws StripeException {
        System.out.println("Strip");
        Stripe.apiKey = secretKey;

        // ✅ Product data
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getName())
                        .build();

        // ✅ Price data
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(
                                productRequest.getCurrency() == null
                                        ? "usd"
                                        : productRequest.getCurrency().toLowerCase()
                        )
                        .setUnitAmount(productRequest.getAmount()) // in cents
                        .setProductData(productData)
                        .build();

        // ✅ Line item
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams.LineItem.builder()
                        .setQuantity(productRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        // ✅ Session params
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:3000/success?session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl("http://localhost:8080/cancel")
                        .setBillingAddressCollection(
                                SessionCreateParams.BillingAddressCollection.REQUIRED)
                        .setShippingAddressCollection(
                                SessionCreateParams.ShippingAddressCollection.builder()
                                        .addAllowedCountry(SessionCreateParams.ShippingAddressCollection.AllowedCountry.valueOf("IN"))
                                        .build()
                        )
                        .addLineItem(lineItem)
                        .build();

        // ✅ Create Stripe session
        Session session = null;

        try{
            session=Session.create(params);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        // ✅ Return response

        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();

    }
}
