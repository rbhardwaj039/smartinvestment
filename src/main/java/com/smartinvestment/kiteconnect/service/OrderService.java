package com.smartinvestment.kiteconnect.service;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.Order;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class OrderService {
    public Order placeOrder(KiteConnect kiteSdk, OrderParams params) throws KiteException, IOException {
        return kiteSdk.placeOrder(params, Constants.VARIETY_AMO);
    }
}