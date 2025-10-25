package com.smartinvestment.kiteconnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.User;
import com.zerodhatech.models.Order;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import jakarta.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class KiteconnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiteconnectApplication.class, args);
    }
}

// @Component
// class KiteRunner {

// 	@Value("${my.app.userId}")
//     private String userId;

//     @Value("${my.app.apiKey}")
//     private String apiKey;

//     @PostConstruct
//     public void run() {

// 		// Initialize Kiteconnect using apiKey.
//         KiteConnect kiteSdk = new KiteConnect(apiKey);
//         System.out.println("Kite SDK: " + kiteSdk);

// 		// Set userId.
//         kiteSdk.setUserId(userId);
// 		System.out.println("User Id: " + userId);

// 		// Get login URL
// 		String url = kiteSdk.getLoginURL();
// 		System.out.println("Login URL: " + url);

// 		// // Get accessToken as follows,
// 		// User user = null;
//         // try {
// 		// 	user = kiteSdk.generateSession(requestToken, apiSecret);
// 		// 	System.out.println("User Access Token: " + user.accessToken);
// 		// 	System.out.println("User Public Token: " + user.publicToken);
// 		// } catch (KiteException | IOException e) {
// 		// 	System.err.println("Error generating session: " + e.getMessage());
// 		// 	e.printStackTrace();
// 		// }

//         // Set request token and public token which are obtained from login process.
//         kiteSdk.setAccessToken(requestToken);
//         // kiteSdk.setPublicToken(user.publicToken);

//         try {
//             Margin margins = kiteSdk.getMargins("equity");
//             System.out.println(margins.available.cash);
//             System.out.println(margins.utilised.debits);
//         } catch (KiteException | IOException e) {
//             System.err.println("Error fetching margins: " + e.getMessage());
//             e.printStackTrace();
//         }

// 		// try {
// 		// 	OrderParams orderParams = new OrderParams();
// 		// 	orderParams.exchange = "NSE";
// 		// 	orderParams.tradingsymbol = "URBANCO";
// 		// 	orderParams.transactionType = "BUY";
// 		// 	orderParams.quantity = 1;
// 		// 	orderParams.orderType = "LIMIT"; // APOs are usually LIMIT orders, but can be MARKET as well
// 		// 	orderParams.price = 100.0; // Set your desired price for LIMIT order
// 		// 	orderParams.product = "CNC";
// 		// 	orderParams.validity = "DAY";

// 		// 	// Place APO order using AMO variety
// 		// 	Order order = kiteSdk.placeOrder(orderParams, Constants.VARIETY_AMO);
// 		// 	System.out.println("APO Order placed. ID: " + order);
// 		// } catch (KiteException | IOException e) {
// 		// 	System.err.println("Error placing APO order: " + e.getMessage());
// 		// 	e.printStackTrace();
// 		// }
    // }
// }