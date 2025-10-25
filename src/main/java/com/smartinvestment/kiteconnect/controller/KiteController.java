package com.smartinvestment.kiteconnect.controller;

import com.smartinvestment.kiteconnect.service.LoginService;
import com.smartinvestment.kiteconnect.service.MarginService;
import com.smartinvestment.kiteconnect.service.OrderService;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.Order;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kite")
public class KiteController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MarginService marginService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public String login() {
        try {
            loginService.login();
            return "Login successful!";
        } catch (KiteException | IOException e) {
            return "Login failed: " + e.getMessage();
        }
    }

    @GetMapping("/margin/{segment}")
    public Margin getMargin(@PathVariable String segment) throws Exception {
        try {
            KiteConnect kiteSdk = loginService.login();
            return marginService.getMargins(kiteSdk, segment);
        } catch (KiteException | IOException e) {
            // Log error and return null or a custom error response
            System.err.println("Error fetching margin: " + e.getMessage());
            return null;
        }
    }

    // @PostMapping("/order")
    // public Order placeOrder(@RequestBody OrderParams params) throws Exception {
    //     KiteConnect kiteSdk = loginService.login();
    //     return orderService.placeOrder(kiteSdk, params);
    // }
}