package com.smartinvestment.kiteconnect.service;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.models.Margin;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class MarginService {
    public Margin getMargins(KiteConnect kiteSdk, String segment) throws KiteException, IOException {
        return kiteSdk.getMargins(segment);
    }
}