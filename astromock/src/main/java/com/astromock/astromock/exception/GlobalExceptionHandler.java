package com.astromock.astromock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildResponse(
            String message,
            HttpStatus status
    ) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );

        return new ResponseEntity<>(response, status);
    }

    /* ========= INVALID INPUT ========= */

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            IllegalArgumentException ex
    ) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /* ========= LOGIN REQUIRED ========= */

    @ExceptionHandler(LoginRequiredException.class)
    public ResponseEntity<ErrorResponse> handleLogin(
            LoginRequiredException ex
    ) {
        return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /* ========= SUBSCRIPTION REQUIRED ========= */

    @ExceptionHandler(SubscriptionRequiredException.class)
    public ResponseEntity<ErrorResponse> handleSubscription(
            SubscriptionRequiredException ex
    ) {
        return buildResponse(ex.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    /* ========= ACCESS DENIED ========= */

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex
    ) {
        System.out.println("hi");
        return buildResponse("Access Denied", HttpStatus.FORBIDDEN);
    }

    /* ========= GENERIC ERROR ========= */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(
            Exception ex
    ) {
        return buildResponse(
                "Something went wrong. Please try again.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}