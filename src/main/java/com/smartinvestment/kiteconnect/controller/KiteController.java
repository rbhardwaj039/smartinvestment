package com.smartinvestment.kiteconnect.controller;

import com.smartinvestment.kiteconnect.service.LoginService;
import com.smartinvestment.kiteconnect.service.MarginService;
import com.smartinvestment.kiteconnect.service.OrderService;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.TokenException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.Order;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/kite")
public class KiteController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MarginService marginService;
    @Autowired
    private OrderService orderService;

    public class ErrorResponse {
        private String error;
        private String errorType;
        private String message;

        public ErrorResponse(String error, String errorType, String message) {
            this.error = error;
            this.errorType = errorType;
            this.message = message;
        }

        // Getters and setters
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getErrorType() { return errorType; }
        public void setErrorType(String errorType) { this.errorType = errorType; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    @GetMapping("/login")
    public String login() {
        try {
            loginService.login();
            return "Login successful!";
        } catch (KiteException e) {
            // Handle Kite-specific trading errors
            System.out.println("Trading error: " + e.getMessage());
            System.out.println("Error type: " + e.getClass().getName());
            // You might want to log the error or take specific actions based on error type
           return null;
        } catch (IOException e) {
            // Handle network/connection errors
            System.out.println("Network error: " + e.getMessage());
            // You might want to implement a retry mechanism here
            return null;
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/margin/{segment}")
    public ResponseEntity<?> getMargin(@PathVariable String segment) throws Exception {
        try {
            KiteConnect kiteSdk = loginService.login();
            Margin margin = marginService.getMargins(kiteSdk, segment);
            return ResponseEntity.ok(margin);  // Returns 200 OK with Margin object
        } catch (TokenException e) {
            ErrorResponse error = new ErrorResponse(
                "Token Error",
                "Error Code" + e.code,
                e.message
            );
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
        } 
        catch (KiteException e) {
            ErrorResponse error = new ErrorResponse(
                "Trading Error",
                e.getClass().getSimpleName(),
                e.getMessage()
            );
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
        } catch (IOException e) {
            // Handle network/connection errors
            System.out.println("Network error: " + e.getMessage() + e.getClass().getName());
            // You might want to implement a retry mechanism here
            return null;
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderParams params) throws Exception {
        try { 
            KiteConnect kiteSdk = loginService.login();
            Order order = orderService.placeOrder(kiteSdk, params);
            return ResponseEntity.ok(order);  // Returns 200 OK with Order object
        } catch (TokenException e) {
            ErrorResponse error = new ErrorResponse(
                "Token Error",
                "Error Code" + e.code,
                e.message
            );
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
        } 
        catch (KiteException e) {
            ErrorResponse error = new ErrorResponse(
                "Kite Error",
                "Error Code" + e.code,
                e.message
            );
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
        } catch (IOException e) {
            // Handle network/connection errors
            System.out.println("Network error: " + e.getMessage() + e.getClass().getName());
            // You might want to implement a retry mechanism here
            return null;
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}